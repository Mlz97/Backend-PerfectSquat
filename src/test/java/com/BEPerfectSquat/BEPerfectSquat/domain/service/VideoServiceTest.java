package com.BEPerfectSquat.BEPerfectSquat.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.BEPerfectSquat.BEPerfectSquat.domain.entity.AnalysisSession;
import com.BEPerfectSquat.BEPerfectSquat.domain.entity.Video;
import com.BEPerfectSquat.BEPerfectSquat.domain.exception.AnalysisSessionNotFoundException;
import com.BEPerfectSquat.BEPerfectSquat.domain.repository.AnalysisSessionRepository;
import com.BEPerfectSquat.BEPerfectSquat.domain.repository.VideoRepository;
import com.BEPerfectSquat.BEPerfectSquat.domain.service.VideoService;

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

    @Test
    void getVideosBySessionId(){
        Long sessionId = 1L;

        AnalysisSession session = new AnalysisSession();
        session.setId(sessionId);

        Video video1 = new Video();
        video1.setSessionId(sessionId);
        video1.setFilePath("video1.mp4");
        
        Video video2 = new Video();
        video2.setSessionId(sessionId);
        video2.setFilePath("video2.mp4");

        List<Video> videos = List.of(video1,video2);

        when(analysisSessionRepository.findById(sessionId))
            .thenReturn(Optional.of(session));
        
        when(videoRepository.findBySessionId(sessionId))
            .thenReturn(videos);

        List<Video> result = videoService.getVideosBySessionId(sessionId);

        assertEquals(2, result.size());
        assertEquals("video1.mp4", result.get(0).getFilePath());
        assertEquals("video2.mp4", result.get(1).getFilePath());
        verify(videoRepository).findBySessionId(sessionId);
    }

    @Test
    void exceptionGetVideoNonExistingSession() {
    Long sessionId = 99L;

    when(analysisSessionRepository.findById(sessionId))
        .thenReturn(Optional.empty());

    assertThrows(
        AnalysisSessionNotFoundException.class,
        () -> videoService.getVideosBySessionId(sessionId)
    );

    verify(videoRepository, never()).findBySessionId(any());
}
}
