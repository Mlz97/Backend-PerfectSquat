package com.BEPerfectSqat.BEPerfectSquat.api.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.BEPerfectSqat.BEPerfectSquat.api.dto.CreateVideoRequest;
import com.BEPerfectSqat.BEPerfectSquat.domain.entity.Video;
import com.BEPerfectSqat.BEPerfectSquat.domain.exception.AnalysisSessionNotFoundException;
import com.BEPerfectSqat.BEPerfectSquat.domain.service.VideoService;

@WebMvcTest(VideoController.class)
public class VideoControllerTest {
    @MockBean
    private VideoService videoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createVideoSuccesfully() throws Exception{
        //GIVEN
        CreateVideoRequest request = new CreateVideoRequest();
        request.setSessionId(1L);
        request.setFilePath("video.mp4");

        Video video = new Video();
        video.setId(10L);
        video.setSessionId(1L);
        video.setFilePath("video.mp4");

        when(videoService.saveVideo(1l, "video.mp4")).thenReturn(video);

        //WHEN + THEN
        mockMvc.perform(post("/video")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sessionId").value(1L))
                .andExpect(jsonPath("$.filePath").value("video.mp4"));
    }

    @Test
    void createVideoValidationError() throws Exception{
        //GIVEN
        CreateVideoRequest request = new CreateVideoRequest();
        request.setSessionId(1l);
        request.setFilePath(""); // invalid

        //WHEN + THEN
        mockMvc.perform(post("/video")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createVideoSessionNotFound() throws Exception{
        //GIVEN
        CreateVideoRequest request = new CreateVideoRequest();
        request.setSessionId(99L);
        request.setFilePath("video.mp4");

        when(videoService.saveVideo(99L, "video.mp4"))
            .thenThrow(new AnalysisSessionNotFoundException(99L));

        // WHEN + THEN
        mockMvc.perform(post("/video")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }
}
