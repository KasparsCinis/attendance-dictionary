package com.attendanceservice.attendance.security.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Header {

    @Value("${jwt.alg}")
    private String alg;
    private String typ;

    public Header() {
        this.typ = "JWT";
    }

    @PostConstruct
    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }


}
