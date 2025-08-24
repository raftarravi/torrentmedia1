package com.torrentmedia.torrentmedia.repository;


import com.torrentmedia.torrentmedia.entity.Influencer;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InfluencerRepository extends MongoRepository<Influencer,String> {

    Influencer findByEmail(String email);
}
