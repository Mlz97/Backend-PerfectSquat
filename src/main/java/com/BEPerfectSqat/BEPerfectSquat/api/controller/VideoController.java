package com.BEPerfectSqat.BEPerfectSquat.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BEPerfectSqat.BEPerfectSquat.api.dto.CreateVideoRequest;
import com.BEPerfectSqat.BEPerfectSquat.api.dto.VideoResponse;
import com.BEPerfectSqat.BEPerfectSquat.domain.entity.Video;
import com.BEPerfectSqat.BEPerfectSquat.domain.service.VideoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/video")
public class VideoController {
    private VideoService videoService;

    public VideoController(VideoService videoService){
        this.videoService= videoService;
    }

    @PostMapping
    public ResponseEntity<VideoResponse> createVideo(@Valid @RequestBody CreateVideoRequest videoRequest) {
        Video video = videoService.saveVideo(
            videoRequest.getSessionId(),
            videoRequest.getFilePath());
        VideoResponse response = VideoResponse.from(video);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VideoResponse>> getVideos(@RequestParam Long sessionId) {
        List<Video> video = videoService.getVideosBySessionId(sessionId);
        List<VideoResponse> response = video.stream().map(VideoResponse::from).toList();
        return ResponseEntity.ok(response);
    }
    
    
}
