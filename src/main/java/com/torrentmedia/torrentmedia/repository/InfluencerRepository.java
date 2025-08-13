package com.torrentmedia.torrentmedia.repository;


import com.torrentmedia.torrentmedia.entity.Influencer;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluencerRepository extends JpaRepository<Influencer,Long> {

    Influencer findByEmail(String email);
}
