package com.BEPerfectSquat.BEPerfectSquat.api.dto.request;

public class UpdateAnalysisResultRequest {

    private Integer totalReps;

    private Integer validReps;

    private String processedVideoFilePath;

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

    public String getProcessedVideoFilePath() {
        return processedVideoFilePath;
    }

    public void setProcessedVideoFilePath(String processedVideoFilePath) {
        this.processedVideoFilePath = processedVideoFilePath;
    }
}
