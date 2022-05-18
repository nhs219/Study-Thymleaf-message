package com.marketProject.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shop/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // https://recordsoflife.tistory.com/248
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody Login login) {
        return memberService.login(login);
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody MemberDTO member) {
        return memberService.join(member);
    }
}
