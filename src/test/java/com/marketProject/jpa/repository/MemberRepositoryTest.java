package com.marketProject.jpa.repository;

import com.marketProject.jpa.entity.Member;
import com.marketProject.domain.member.Grade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    @Rollback(false)
    void testMember() throws Exception {
        //given
        Member member = new Member();
        String password = "memberA";

        member.setPassword(passwordEncoder.encode("password"));
        member.setEmail("test@test.com");
        member.setName("spring");
        member.setPhone("01000001111");
        member.setGrade(Grade.FAMILY);
        //when
        Long saveId = memberRepository.save(member).getId();
        Member findMember = memberRepository.findOne(saveId);

        //then
        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());

    }

}