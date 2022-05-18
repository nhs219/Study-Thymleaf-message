package com.marketProject.domain.jpa.Repository;

import com.marketProject.domain.jpa.entity.Member;
import com.marketProject.domain.member.Grade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setPassword("memberA");
        member.setEmail("test@test.com");
        member.setAddress("서울");
        member.setName("spring");
        member.setPhone("01000001111");
        member.setGrade(Grade.FAMILY);
        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
    }

}