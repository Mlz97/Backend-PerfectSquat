package com.BEPerfectSquat.BEPerfectSquat.domain.entity;
import java.time.LocalDateTime;

import com.BEPerfectSquat.BEPerfectSquat.domain.enums.AnalysisSessionState;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
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

    @Column(name = "total_reps")
    private Integer totalReps;

    @Column(name = "valid_reps")
    private Integer validReps;

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

    public Integer getTotalReps() {
        return totalReps;
    }

    public void setTotalReps(Integer totalReps) {
        this.totalReps = totalReps;
    }

    public Integer getValidReps() {
        return validReps;
    }

    public void setValidReps(Integer validReps) {
        this.validReps = validReps;
    }

}
