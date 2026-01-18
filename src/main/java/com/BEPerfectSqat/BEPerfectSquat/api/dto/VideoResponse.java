package com.BEPerfectSqat.BEPerfectSquat.api.dto;

import java.time.LocalDateTime;

import com.BEPerfectSqat.BEPerfectSquat.domain.entity.Video;

public class VideoResponse {
    private Long id;
    private Long sessionId;
    private String filePath;
    private LocalDateTime uploadedAt;
    
    public VideoResponse(Long id, Long sessionId, String filePath, LocalDateTime uploadedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
    }

    public static VideoResponse from(Video video){
        return new VideoResponse(
            video.getId(),
            video.getSessionId(),
            video.getFilePath(),
            video.getUploadedAt()
        );
    }

    public Long getId() {
        return id;
    }
    public Long getSessionId() {
        return sessionId;
    }
    public String getFilePath() {
        return filePath;
    }
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    
}
