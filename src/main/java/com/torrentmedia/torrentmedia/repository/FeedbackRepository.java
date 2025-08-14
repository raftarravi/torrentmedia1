package com.torrentmedia.torrentmedia.repository;

import com.torrentmedia.torrentmedia.entity.Feedback;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

    List<Feedback> findAllByServiceType(String serviceType);
}
