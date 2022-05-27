package com.marketProject.jpa.repository;

import com.marketProject.jpa.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {

    private final EntityManager em;

    public void save(Company company) {

        company.setCreateDatetime(LocalDateTime.now());
        em.persist(company);
    }

    public Company findOne(Long id) {
        return em.find(Company.class, id);
    }

    public List<Company> findAll() {
        return em.createQuery("select c from Company c", Company.class)
                .getResultList();
    }

    public List<Company> findByCompanyNum(String companyNum) {
        return em.createQuery("select c from Company c where c.companyNum =: companyNum", Company.class)
                .setParameter("companyNum", companyNum)
                .getResultList();
    }
}
