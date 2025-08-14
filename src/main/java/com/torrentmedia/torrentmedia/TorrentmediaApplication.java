package com.torrentmedia.torrentmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class TorrentmediaApplication {

	public static void main(String[] args) {

		SpringApplication.run(TorrentmediaApplication.class, args);
	}

}
