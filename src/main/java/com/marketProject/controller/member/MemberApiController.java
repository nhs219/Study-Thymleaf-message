package com.marketProject.controller.member;

import com.marketProject.common.Result;
import com.marketProject.common.ResultList;
import com.marketProject.domain.member.Login;
import com.marketProject.jpa.entity.Member;
import com.marketProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody Login login) {
        return memberService.login(login);
    }

    /**
     * entity를 그대로 갖다 쓰기엔...
     * form과 차이가 많고 실무에서는 조건 추가를 많이 하다보면 entity가 너무 지저분해짐.
     *
     * MemberForm과 같은 class를 하나 만들어서 화면에서 넘어오는 값는 entity가 아닌 form이나 Dto에서 validation 해야한다.
     * API를 사용하면 dto로 넘기는 게 좋음. entity를 바로 넘기면 안됨!
     *
     * 나는 API를 개발해야하니까 구현한거 하나씩 고치기
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/join")
    public Result saveMember(@RequestBody @Valid CreateMemberRequest request) {
        Member member = request.setMember();
        Member saveMember = memberService.join(member);
        CreateMemberResponse response = new CreateMemberResponse(saveMember.getId(), saveMember.getName(), saveMember.getEmail(), LocalDateTime.now());
        return new Result(response);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{memberId}")
    public Result updateMember(@PathVariable("memberId") Long id,
                                             @RequestBody @Valid UpdateMemberRequest request) {
        Member updateMember = memberService.updateMember(id, request);
        UpdateMemberResponse response = new UpdateMemberResponse(updateMember.getId(), updateMember.getName());
        return new Result(response);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResultList findMembers() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getId(), m.getName(), m.getEmail(), m.getAddress(), m.getPhone()))
                .collect(Collectors.toList());
        return new ResultList(collect.size(), collect);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{memberId}")
    public Result findMember(@PathVariable("memberId") Long id) {

        Member findMember = memberService.findMember(id);
        MemberDto response = new MemberDto(findMember.getId(), findMember.getName(), findMember.getEmail(),
                findMember.getAddress(), findMember.getPhone());
        return new Result(response);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable("memberId") Long id) {
        memberService.deleteMember(id);
    }
}
