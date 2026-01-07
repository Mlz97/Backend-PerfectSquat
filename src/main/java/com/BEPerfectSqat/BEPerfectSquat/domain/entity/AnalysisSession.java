package com.BEPerfectSqat.BEPerfectSquat.domain.entity;
import java.time.LocalDateTime;

import com.BEPerfectSqat.BEPerfectSquat.domain.enums.AnalysisSessionState;

import jakarta.persistence.*;
@Entity
@Table(name = "analysis_session")
public class AnalysisSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AnalysisSessionState state = AnalysisSessionState.WAITING;

    private LocalDateTime createdAt;

    private Long userId;

}
