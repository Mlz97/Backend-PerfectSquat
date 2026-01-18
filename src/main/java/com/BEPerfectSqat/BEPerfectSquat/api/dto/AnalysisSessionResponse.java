package com.BEPerfectSqat.BEPerfectSquat.api.dto;

import java.time.LocalDateTime;

import com.BEPerfectSqat.BEPerfectSquat.domain.entity.AnalysisSession;
import com.BEPerfectSqat.BEPerfectSquat.domain.enums.AnalysisSessionState;

//Modelo de salida de la API(Contrato público)
public class AnalysisSessionResponse {
    private Long id;
    private AnalysisSessionState state;
    private LocalDateTime createdAt;

    public AnalysisSessionResponse(Long id, AnalysisSessionState state, LocalDateTime createdAt){
        this.id=id;
        this.state=state;
        this.createdAt=createdAt;
    }

    public Long getId() {
        return id;
    }

    public AnalysisSessionState getState() {
        return state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
