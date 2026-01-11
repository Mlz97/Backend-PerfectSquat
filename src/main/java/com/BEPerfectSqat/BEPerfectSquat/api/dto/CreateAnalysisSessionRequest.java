package com.BEPerfectSqat.BEPerfectSquat.api.dto;

import jakarta.validation.constraints.NotNull;

public class CreateAnalysisSessionRequest {
    @NotNull
    private Long id;
    private Enum state;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Enum getState() {
        return state;
    }
    public void setState(Enum state) {
        this.state = state;
    }
}
