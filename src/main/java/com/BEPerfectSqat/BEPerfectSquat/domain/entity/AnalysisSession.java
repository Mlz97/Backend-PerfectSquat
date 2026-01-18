package com.BEPerfectSqat.BEPerfectSquat.domain.entity;
import java.time.LocalDateTime;

import com.BEPerfectSqat.BEPerfectSquat.domain.enums.AnalysisSessionState;

import jakarta.persistence.*;
@Entity
@Table(name = "analysis_session")
//Modelo de dominio + persistencia(JPA)
public class AnalysisSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AnalysisSessionState state = AnalysisSessionState.WAITING;

    private LocalDateTime createdAt;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnalysisSessionState getState() {
        return state;
    }

    public void setState(AnalysisSessionState state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
