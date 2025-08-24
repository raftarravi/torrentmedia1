package com.torrentmedia.torrentmedia.repository;

import com.torrentmedia.torrentmedia.entity.NewsLetter;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsLetterRepo extends MongoRepository<NewsLetter,String> {


}
