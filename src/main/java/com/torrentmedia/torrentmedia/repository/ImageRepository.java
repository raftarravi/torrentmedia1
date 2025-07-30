package com.torrentmedia.torrentmedia.repository;

import com.torrentmedia.torrentmedia.entity.Image;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image,String> {
     Image findByRegisterEmailId(String registerEmailId);

}
