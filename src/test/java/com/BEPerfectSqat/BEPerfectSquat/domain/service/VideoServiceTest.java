package com.BEPerfectSqat.BEPerfectSquat.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.BEPerfectSqat.BEPerfectSquat.domain.entity.AnalysisSession;
import com.BEPerfectSqat.BEPerfectSquat.domain.entity.Video;
import com.BEPerfectSqat.BEPerfectSquat.domain.exception.AnalysisSessionNotFoundException;
import com.BEPerfectSqat.BEPerfectSquat.domain.repository.AnalysisSessionRepository;
import com.BEPerfectSqat.BEPerfectSquat.domain.repository.VideoRepository;
@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {
    @Mock
    private VideoRepository videoRepository;

    @Mock
    private AnalysisSessionRepository analysisSessionRepository;

    @InjectMocks
    private VideoService videoService;

    @Test
    void saveVideoOk(){
        //GIVEN
        Long sessionId = 1L;
        String filePath = "video.mp4";

        AnalysisSession session = new AnalysisSession();
        session.setId(sessionId);

        Video saveVideo = new Video();
        saveVideo.setSessionId(sessionId);
        saveVideo.setFilePath(filePath);

        when(analysisSessionRepository.findById(sessionId))
            .thenReturn(Optional.of(session));
        
        when(videoRepository.save(any(Video.class)))
            .thenReturn(saveVideo);

        //WHEN + THEN

        Video result =  videoService.saveVideo(sessionId, filePath);

        assertNotNull(result);
        assertEquals(sessionId, result.getSessionId());
        assertEquals(filePath, result.getFilePath());

        verify(videoRepository).save(any(Video.class));
    }

    @Test
    void exceptionWhenSessionNotExists(){
        //GIVEN
        Long sessionId = 99L;
        String filePath = "video.mp4";

        when(analysisSessionRepository.findById(sessionId)).thenReturn(Optional.empty());

        //WHEN + THEN
        assertThrows(AnalysisSessionNotFoundException.class,()->videoService.saveVideo(sessionId,filePath));

        verify(videoRepository, never()).save(any());
}
}
