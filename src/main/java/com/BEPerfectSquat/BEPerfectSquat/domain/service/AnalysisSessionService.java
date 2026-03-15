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
public class AnalysisSessionService {
    private final AnalysisSessionRepository analysisSessionRepository;
    private final VideoRepository videoRepository;

    public AnalysisSessionService(AnalysisSessionRepository analysisSessionRepository, VideoRepository videoRepository){
        this.analysisSessionRepository = analysisSessionRepository;
        this.videoRepository = videoRepository;
    }

    public AnalysisSession createSession(){
        AnalysisSession newSession = new AnalysisSession();
        newSession.setState(AnalysisSessionState.WAITING);
        newSession.setCreatedAt(LocalDateTime.now());
        return analysisSessionRepository.save(newSession);
    }

    public AnalysisSession getSessionById(Long id){
        AnalysisSession session = analysisSessionRepository.findById(id)
        .orElseThrow(()->new AnalysisSessionNotFoundException(id));
        return session;
    }

    public AnalysisSession updateSessionWithResults(Long id, Integer totalReps, Integer validReps, String processedVideoFilePath){
        AnalysisSession session = analysisSessionRepository.findById(id)
            .orElseThrow(() -> new AnalysisSessionNotFoundException(id));

        if (session.getState() != AnalysisSessionState.PROCESSING) {
            throw new IllegalStateException(
                "Solo se pueden añadir resultados a sesiones en estado PROCESSING. Estado actual: " + session.getState());
        }

        session.setTotalReps(totalReps);
        session.setValidReps(validReps);
        session.setState(AnalysisSessionState.FINISHED);

        // Si se proporciona ruta del vídeo procesado->actualizar el video asociado
        if (processedVideoFilePath != null && !processedVideoFilePath.isBlank()) {
            List<Video> videos = videoRepository.findBySessionId(id);
            if (!videos.isEmpty()) {
                Video video = videos.get(0);
                video.setProcessedFilePath(processedVideoFilePath);
                videoRepository.save(video);
            }
        }

        return analysisSessionRepository.save(session);
    }
}
