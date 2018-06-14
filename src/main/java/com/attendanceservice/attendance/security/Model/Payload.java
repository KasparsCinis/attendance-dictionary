package com.attendanceservice.attendance.security.Model;

import org.springframework.stereotype.Component;

@Component
public class Payload {

    private String iss;
    private String jti;
    private String exp;
    private String iat;
    private String name;
    private String ip;
    private String role;
    private String csrf;

    public Payload(String iss, String jti, String exp, String iat, String name, String ip, String role, String csrf) {
        this.iss = iss;
        this.jti = jti;
        this.exp = exp;
        this.iat = iat;
        this.name = name;
        this.ip = ip;
        this.role = role;
        this.csrf = csrf;
    }

    public Payload(){

    }


    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCsrf() {
        return csrf;
    }

    public void setCsrf(String csrf) {
        this.csrf = csrf;
    }
}
