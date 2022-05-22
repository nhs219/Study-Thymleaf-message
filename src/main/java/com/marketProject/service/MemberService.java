package com.marketProject.service;

import com.marketProject.domain.member.Login;
import com.marketProject.jpa.entity.Member;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    public ResponseEntity<String> login(Login login);
    public Member join(Member member);
    public List<Member> findMembers();
    public Member findOne(Long memberId);
}
