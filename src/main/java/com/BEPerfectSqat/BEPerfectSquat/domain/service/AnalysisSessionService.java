package com.BEPerfectSqat.BEPerfectSquat.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.BEPerfectSqat.BEPerfectSquat.domain.entity.AnalysisSession;
import com.BEPerfectSqat.BEPerfectSquat.domain.enums.AnalysisSessionState;
import com.BEPerfectSqat.BEPerfectSquat.domain.exception.AnalysisSessionNotFoundException;
import com.BEPerfectSqat.BEPerfectSquat.domain.repository.AnalysisSessionRepository;

public class AnalysisSessionService {
    private final AnalysisSessionRepository analysisSessionRepository;
    public AnalysisSessionService(AnalysisSessionRepository analysisSessionRepository){
        this.analysisSessionRepository= analysisSessionRepository;
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
}
