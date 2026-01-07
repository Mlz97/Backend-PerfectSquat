package com.BEPerfectSqat.BEPerfectSquat.api.dto;

public class CreateVideoRequest {
    private Long sessionId;
    private String filePath;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    
}
