package com.marketProject.controller.member;

import com.marketProject.jpa.entity.Member;
import com.marketProject.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        //exception 넣어줘야함. 찾지 못하면 오류!
        Member findMember = memberRepository.findByEmail(userPk).get(0);

        if(findMember == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        return (UserDetails) findMember;

    }
}
