package com.marketProject.service;

import com.marketProject.controller.member.UpdateMemberRequest;
import com.marketProject.domain.member.Login;
import com.marketProject.jpa.entity.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    public ResponseEntity<String> login(Login login);
    public Member join(Member member);
    public Member updateMember(Long id, UpdateMemberRequest request);
    public List<Member> findMembers();
    public Member findMember(Long memberId);
    public void deleteMember(Long id);
}
