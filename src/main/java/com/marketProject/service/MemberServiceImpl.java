package com.marketProject.service;

import com.marketProject.configuration.Jwt.JwtTokenProvider;
import com.marketProject.controller.member.UpdateMemberRequest;
import com.marketProject.jpa.entity.Address;
import com.marketProject.jpa.entity.Member;
import com.marketProject.jpa.repository.MemberRepository;
import com.marketProject.jpa.repository.MemberSvcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MemberSvcRepository memberSvcRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<String> login(String email, String password) throws NoSuchFieldException {

        List<Member> members = memberRepository.findByEmail(email);

        if(members.size() < 1) {
            throw new InternalAuthenticationServiceException("Cannot find member");
        }

        Member member = members.get(0);
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        String jwtToken = jwtTokenProvider.generateToken(member.getEmail(), member.getRoles());
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-AUTH-TOKEN", jwtToken);
        ResponseEntity<String> response = new ResponseEntity<>(null, headers, HttpStatus.ACCEPTED);

        return response;
    }

    public ResponseEntity<String> logout() {
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
        member.joinMember(member);
        // 비밀번호 암호화 해서 insert
        String password = passwordEncoder.encode(member.getPassword());
        return memberRepository.save(member, password);
    }

    @Transactional
    public Member updateMember(Long id, UpdateMemberRequest request) {
        Member member = memberRepository.findOne(id);

        // if를 controller단에 둬야하나??
        if (request.getName() != null) {
            member.setName(request.getName());
        }
        if (request.getPhone() != null) {
            member.setPhone(request.getPhone());
        }

        if (request.getZipcode() != null
                || request.getStreet() != null
                || request.getDetailAddress() != null) {
            member.setAddress(new Address(request.getZipcode(), request.getStreet(), request.getDetailAddress()));
        }

        if (request.getPassword() != null) {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            member.getMemberSvc().setUpdatePwdDate(LocalDate.now());
        }
        member.getMemberSvc().setUpdateDatetime(LocalDateTime.now());

        return member;
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(Long id) {
        return memberRepository.findOne(id);
    }
    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findOne(id);
        member.getMemberSvc().setDeleteDatetime(LocalDateTime.now());
    }

    private void validateDuplicateMember(Member member) {
        // 동시에 같은 email로 가입하는 경우를 방지하기위해 member table에 unique 제약 조건을 넣는 것이 좋음
        List <Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (findMembers.size() > 0) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
