package com.marketProject.service;

import com.marketProject.jpa.entity.Address;
import com.marketProject.jpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
//@Rollback(value = false)
public class MemberServiceTest {

    /**
     * 스프링, db 와 엮어서 짠 테스트 코드보다
     * 순수하게 메서드를 단위테스트하는 것이 좋은 테스트이다.
     * 이후에 바꾸기로. 지금은 jpa 공부 중이라 이렇게 두기로,,,
     */

    @Autowired PasswordEncoder passwordEncoder;
    @Autowired MemberServiceImpl memberService;
    @Autowired EntityManager em;

    @Test
    void 비밀번호_암호화() {
        //given
        String rawPassword = "test";

        //when
        String encodedPassword = passwordEncoder.encode(rawPassword);

        //then
        assertAll(
                () -> assertNotEquals(rawPassword, encodedPassword),
                () -> assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
        );
    }

    @Test
    void 회원가입() {
        //given
        Member member = makeMember("testjoin@test.com");

        //when
        Long memberId = memberService.join(member).getId();

        //then
        assertThat(memberId).isEqualTo(member.getId());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = makeMember("test1@test.com");
        Member member2 = makeMember("test1@test.com");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    }

    @Test
    void 전체회원조회() {
        //given
        Member member = makeMember("findMembers@test.com");
        em.persist(member);

        //when
        int memberSize = memberService.findMembers().size();

        //then
        assertThat(memberSize).isEqualTo(1);
    }

    @Test
    void 회원조회() {
        //given
        String email = "findOne@test.com";
        Member member = makeMember(email);
        memberService.join(member);

        //when
        Member findOne = memberService.findMember(member.getId());

        //then
        assertThat(findOne.getEmail()).isEqualTo(email);

    }

    private Member makeMember(String email) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword("1234");
        member.setName("test");
        member.setPhone("01011111111");
        member.setAddress(new Address("singil-ro", "1234", "test"));
        return member;
    }
}
