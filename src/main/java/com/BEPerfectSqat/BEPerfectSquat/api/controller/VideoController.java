package com.BEPerfectSqat.BEPerfectSquat.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BEPerfectSqat.BEPerfectSquat.api.dto.CreateVideoRequest;
import com.BEPerfectSqat.BEPerfectSquat.domain.entity.Video;
import com.BEPerfectSqat.BEPerfectSquat.domain.service.VideoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/videos")
public class VideoController {
    private VideoService videoService;

    public VideoController(VideoService videoService){
        this.videoService= videoService;
    }

    @PostMapping
    public Video createVideo(@RequestBody CreateVideoRequest videoRequest) {


        return videoService.saveVideo(videoRequest.getSessionId(), videoRequest.getFilePath());
    }
    
}
