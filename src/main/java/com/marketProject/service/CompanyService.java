package com.marketProject.service;

import com.marketProject.jpa.entity.Company;

import java.util.List;

public interface CompanyService {

    public Company saveCompany(Company company);
    public List<Company> findCompanies();
    public Company findCompany(Long companyId);
}
