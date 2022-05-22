package com.marketProject.service;

import com.marketProject.jpa.entity.Address;
import com.marketProject.jpa.entity.Company;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
//@Rollback(value = false)
class CompanyServiceTest {

    @Autowired CompanyServiceImpl companyService;

    @Test
    void 회사등록() {
        //given
        Company company = makeCompany("test1234");

        //when
        companyService.saveCompany(company);
        Long findCompanyId = companyService.findCompany(company.getId()).getId();

        //then
        Assertions.assertThat(company.getId()).isEqualTo(findCompanyId);
    }

    @Test
    void 중복_회사_예외() {
        //given
        Company company1 = makeCompany("test-companyNum");
        Company company2 = makeCompany("test-companyNum");

        //when
        companyService.saveCompany(company1);

        //then
        assertThrows(IllegalStateException.class, () -> companyService.saveCompany(company2));
    }

    @Test
    void 전체회사조회() {

    }

    @Test
    void 회사조회() {

    }

    private Company makeCompany(String companyNum) {
        Company company = new Company();

        company.setName("testCompany");
        company.setPhone("021112222");
        company.setCompanyNum(companyNum);
        company.setEmail("test@test.com");
        company.setAddress(new Address("강남", "1111", "어딘가"));
        company.setCreateDatetime(LocalDateTime.now());

        return company;
    }
}