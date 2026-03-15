package com.BEPerfectSquat.BEPerfectSquat.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BEPerfectSquat.BEPerfectSquat.domain.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

    List<Video> findBySessionId(Long sessionId);

}


