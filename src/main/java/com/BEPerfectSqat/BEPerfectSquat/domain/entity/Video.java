package com.BEPerfectSqat.BEPerfectSquat.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "video")
//Modelo de dominio + persistencia(JPA)
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long sessionId;

    private LocalDateTime uploadedAt;

    private String filePath;

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public String getFilePath() {
        return filePath;
    }
    

}
