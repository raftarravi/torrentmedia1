package com.torrentmedia.torrentmedia.service;

import com.torrentmedia.torrentmedia.entity.Influencer;
import com.torrentmedia.torrentmedia.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfluencerService {

    @Autowired
    InfluencerRepository influencerRepository;
    public List<Influencer> getAllInfluencers() {
        return influencerRepository.findAll();

    }
}
