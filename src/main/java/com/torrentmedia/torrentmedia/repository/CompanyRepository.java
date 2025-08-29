package com.torrentmedia.torrentmedia.repository;



import com.torrentmedia.torrentmedia.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
}
