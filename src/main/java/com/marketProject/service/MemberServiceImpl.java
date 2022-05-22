package com.marketProject.service;

import com.marketProject.domain.member.Grade;
import com.marketProject.domain.member.Login;
import com.marketProject.jpa.entity.Member;
import com.marketProject.jpa.repository.MemberRepository;
import com.marketProject.jpa.repository.MemberSvcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MemberSvcRepository memberSvcRepository;

    @Override
    public ResponseEntity<String> login(Login login) {

        String sessionUUID = UUID.randomUUID().toString();

        log.info("sessionUUID : {} ", sessionUUID );

        return null;
    }

    /**
     * 회원 가입
     */
    @Override
    @Transactional
    public Member join(Member member) {
        // 중복 회원 검증
        validateDuplicateMember(member);

        // 회원 등록
        member.setGrade(Grade.FAMILY);
        memberRepository.save(member);

        // 응답
        return member;
    }

    private void validateDuplicateMember(Member member) {
        // 동시에 같은 email로 가입하는 경우를 방지하기위해 de member table에 unique 제약 조건을 넣는 것이 좋음
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
