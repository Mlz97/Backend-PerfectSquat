package com.BEPerfectSqat.BEPerfectSquat.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BEPerfectSqat.BEPerfectSquat.domain.entity.AnalysisSession;
import com.BEPerfectSqat.BEPerfectSquat.domain.service.AnalysisSessionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/session")
public class AnalysisSessionController {
    private AnalysisSessionService analysisSessionService;

    public AnalysisSessionController(AnalysisSessionService analysisSessionService){
        this.analysisSessionService=analysisSessionService;
    }

    @PostMapping
    public AnalysisSession createSession(){
        return analysisSessionService.createSession();
    }

    @GetMapping("/{id}")
    public AnalysisSession getAnalysisSessionId(@PathVariable Long id) {
        return analysisSessionService.getSessionById(id);
    }
    
    
}
