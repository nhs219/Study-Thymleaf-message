package com.marketProject.service;

import com.marketProject.jpa.entity.Company;
import com.marketProject.jpa.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Transactional
    public Company saveCompany(Company company) {
        // 중복 확인(사업자 등록번호)
        validateDuplicateCompany(company);

        // 등록
        companyRepository.save(company);

        // 응답
        return company;
    }

    public List<Company> findCompanies() {
        return companyRepository.findAll();
    }

    public Company findCompany(Long companyId) {
        return companyRepository.findOne(companyId);
    }

    private void validateDuplicateCompany(Company company) {
        List<Company> findCompanies = companyRepository.findByCompanyNum(company.getCompanyNum());
        if (!findCompanies.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회사입니다.");
        }
    }
}
