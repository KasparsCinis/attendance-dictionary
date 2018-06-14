package com.attendanceservice.attendance.security;

import com.attendanceservice.attendance.security.Model.Header;
import com.attendanceservice.attendance.security.Model.Payload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Component
public class JwtToken {

    private final Header header;

    @Value(value = "${jwt.expires_in_minutes}")
    private Long expiresInMinutes;

    @Value(value = "${jwt.secret}")
    private String secret;


    private final ObjectMapper jacksonObjectMapper;

    @Autowired
    public JwtToken(Header header, ObjectMapper jacksonObjectMapper) {
        this.header = header;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    public boolean verifyToken(String jwtToken) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        String removeBearer = jwtToken.substring(7);

        String parts[] = removeBearer.split("\\.");
        System.out.println(parts.length);

        System.out.println(removeBearer);

        String header = parts[0];
        System.out.println(header);
        String payload = parts[1];
        String signature = parts[2];
        System.out.println("signature" + signature);
        String message = header + "." + payload;
        System.out.println("Message: " + message);

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
        System.out.println("Hash: " + hash);
        extractPayload(payload);
        return hash.equals(signature);

    }

    private Payload extractedPayload;

    private void extractPayload(String base64EncodedString) throws IOException {

        String jsonString = new String(Base64Utils.decodeFromUrlSafeString(base64EncodedString), StandardCharsets.UTF_8);
        System.out.println(jsonString);
        extractedPayload = jacksonObjectMapper.readValue(jsonString, Payload.class);
    }

    public boolean isValid() {
        return (Long.parseLong(extractedPayload.getExp())>System.currentTimeMillis());
    }

}
