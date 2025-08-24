package com.torrentmedia.torrentmedia.repository;

import com.torrentmedia.torrentmedia.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
