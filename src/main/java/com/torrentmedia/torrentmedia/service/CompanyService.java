package com.torrentmedia.torrentmedia.service;


import com.torrentmedia.torrentmedia.entity.Company;
import com.torrentmedia.torrentmedia.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {

        return companyRepository.findAll();
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(String id) {
        return companyRepository.findById(id).orElse(null);
    }

    public void deleteCompany(String id) {
        companyRepository.deleteById(id);
    }
}


