package com.BEPerfectSqat.BEPerfectSquat.domain.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.BEPerfectSqat.BEPerfectSquat.domain.entity.AnalysisSession;
import com.BEPerfectSqat.BEPerfectSquat.domain.entity.Video;
import com.BEPerfectSqat.BEPerfectSquat.domain.exception.AnalysisSessionNotFoundException;
import com.BEPerfectSqat.BEPerfectSquat.domain.repository.AnalysisSessionRepository;
import com.BEPerfectSqat.BEPerfectSquat.domain.repository.VideoRepository;



@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final AnalysisSessionRepository analysisSessionRepository;
    
    public VideoService(VideoRepository videoRepository, AnalysisSessionRepository analysisSessionRepository){
        this.videoRepository=videoRepository;
        this.analysisSessionRepository=analysisSessionRepository;
    }

    public Video saveVideo(Long sessionId, String filePath){
        AnalysisSession session = analysisSessionRepository.findById(sessionId)
            .orElseThrow(()->new AnalysisSessionNotFoundException(sessionId));
        Video newVideo = new Video();
        newVideo.setFilePath(filePath);
        newVideo.setSessionId(sessionId);
        newVideo.setUploadedAt(LocalDateTime.now());
        return videoRepository.save(newVideo);
    }

    public List<Video> getVideosBySessionId(Long sessionId){
            AnalysisSession session = analysisSessionRepository.findById(sessionId)
            .orElseThrow(()->new AnalysisSessionNotFoundException(sessionId));
            
            return videoRepository.findBySessionId(sessionId);
    }
}
