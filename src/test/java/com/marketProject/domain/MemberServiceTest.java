package com.marketProject.domain;

import com.marketProject.jpa.entity.Address;
import com.marketProject.jpa.entity.Member;
import com.marketProject.jpa.repository.MemberRepository;
import com.marketProject.service.MemberServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired PasswordEncoder passwordEncoder;
    @Autowired MemberServiceImpl memberService;

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
    void 회원가입() throws Exception {
        //given
        Member member = makeMember("test@test.com");

        //when
        Member response = memberService.join(member);
        Long findMemberId = memberService.findOne(member.getId()).getId();

        //then
        assertThat(findMemberId).isEqualTo(member.getId());
    }

    @Test
    void 중복_회원_예외() throws Exception {
        //given
        Member member1 = makeMember("test1@test.com");
        Member member2 = makeMember("test1@test.com");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    }

    @Test
    void 전체회원조회() throws Exception {
        //given
        Member member = makeMember("findMembers@test.com");
        memberService.join(member);

        //when
        JSONArray members = new JSONArray(memberService.findMembers());

        //then
        assertInstanceOf(JSONArray.class, members);
    }

    @Test
    void 회원조회() throws Exception {
        //given
        Member member = makeMember("findOne@test.com");
        memberService.join(member);

        //when
        Member findOne = memberService.findOne(member.getId());

        //then
        assertThat(member.getId()).isEqualTo(findOne.getId());

    }

    private Member makeMember(String email) {
        Member member = new Member();
        member.setEmail("test@test.com");
        member.setPassword("1234");
        member.setName("test");
        member.setPhone("01011111111");
        member.setAddress(new Address("singil-ro", "1234", "test"));
        return member;
    }
}
