package com.marketProject.domain.member;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    public ResponseEntity<String> login(Login login);
    public ResponseEntity<String> join(MemberDTO member);
}
