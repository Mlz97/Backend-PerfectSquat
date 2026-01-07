package com.BEPerfectSqat.BEPerfectSquat.domain.service;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.BEPerfectSqat.BEPerfectSquat.domain.entity.Video;
import com.BEPerfectSqat.BEPerfectSquat.domain.repository.VideoRepository;



@Service
public class VideoService {
    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository){
        this.videoRepository=videoRepository;
    }

    public Video saveVideo(Long sessionId, String filePath){
        Video newVideo = new Video();
        newVideo.setFilePath(filePath);
        newVideo.setSessionId(sessionId);
        newVideo.setUploadedAt(LocalDateTime.now());
        return videoRepository.save(newVideo);
    }
}
