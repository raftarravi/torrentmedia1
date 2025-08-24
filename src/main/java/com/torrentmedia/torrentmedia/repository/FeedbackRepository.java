package com.torrentmedia.torrentmedia.repository;

import com.torrentmedia.torrentmedia.entity.Feedback;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface FeedbackRepository extends MongoRepository<Feedback,String> {

    List<Feedback> findAllByServiceType(String serviceType);
}
