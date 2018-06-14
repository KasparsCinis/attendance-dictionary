package com.attendanceservice.attendance.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "dictionary")
public class Word {

    @Id
    @Column(name = "uid")
    @Type(type = "pg-uuid")
    private UUID uid;
    private String word;
    private String description;

    public Word(UUID uid, String word, String description) {
        this.uid = uid;
        this.word = word;
        this.description = description;
    }

    public Word(String word, String description) {
        this.word = word;
        this.description = description;
    }

    public Word() {

    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
