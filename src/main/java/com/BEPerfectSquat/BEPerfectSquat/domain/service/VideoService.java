package com.BEPerfectSquat.BEPerfectSquat.domain.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.BEPerfectSquat.BEPerfectSquat.domain.entity.AnalysisSession;
import com.BEPerfectSquat.BEPerfectSquat.domain.entity.Video;
import com.BEPerfectSquat.BEPerfectSquat.domain.enums.AnalysisSessionState;
import com.BEPerfectSquat.BEPerfectSquat.domain.exception.AnalysisSessionNotFoundException;
import com.BEPerfectSquat.BEPerfectSquat.domain.repository.AnalysisSessionRepository;
import com.BEPerfectSquat.BEPerfectSquat.domain.repository.VideoRepository;



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
        if(session.getState() != AnalysisSessionState.WAITING){
            throw new IllegalArgumentException("No se pueden subir videos cuando la sesión está en estado "+ session.getState());
        }
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
