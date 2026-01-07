package com.BEPerfectSqat.BEPerfectSquat.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BEPerfectSqat.BEPerfectSquat.domain.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

    List<Video> findBySessionId(Long sessionId);

}


