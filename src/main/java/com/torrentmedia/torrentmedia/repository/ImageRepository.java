package com.torrentmedia.torrentmedia.repository;

import com.torrentmedia.torrentmedia.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image , Long> {
     Image findByRegisterEmailId(String registerEmailId);

}
