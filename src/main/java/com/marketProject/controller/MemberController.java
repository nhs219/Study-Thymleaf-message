package com.marketProject.controller;

import com.marketProject.domain.member.Login;
import com.marketProject.jpa.entity.Member;
import com.marketProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shop/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody Login login) {
        return memberService.login(login);
    }

    @PostMapping("/join")
    @ResponseBody
    public Member join(@Valid @RequestBody Member member) {
        return memberService.join(member);
    }

    @GetMapping
    @ResponseBody
    public List<Member> findMembers() {
        return memberService.findMembers();
    }

    @GetMapping("/{memberId}")
    @ResponseBody
    public Member findMember(@PathVariable("memberId") Long memberId) {
        return memberService.findOne(memberId);
    }
}
