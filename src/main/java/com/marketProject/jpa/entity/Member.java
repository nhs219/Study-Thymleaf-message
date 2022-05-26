package com.marketProject.jpa.entity;

import com.marketProject.domain.member.Grade;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

/**
 * 연관관계는 무조건 지연로딩으로 설정(EAGER는 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어렵다.)
 * 만약 엔티티를 함께 조회하고싶으면, fetch join 또는 연관관계 그래프 기능 사용
 * XToOne(OneToOne, ManyToOne)관계는 default가 즉시로딩이어서 지연로딩으로 설정하여야 한다.
 *
 * XToOne 관계는 페치조인 해도 된다.
 *
 */
@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToOne(mappedBy = "member", fetch = LAZY, cascade = ALL)
    private MemberSvc memberSvc;

    private String email;
    private String password;
    private String name;
    private String phone;
    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "member", cascade = ALL) //cascade.ALL로 해놓으면 persist 여러번 할 걸 한번만 해도 됨
    private List<Order> orders = new ArrayList<>();

    public void setMemberSvc(MemberSvc memberSvc) {
        this.memberSvc = memberSvc;
        memberSvc.setMember(this);
    }

    public Member joinMember(Member member) {
        MemberSvc memberSvc = new MemberSvc();
        member.setGrade(Grade.FAMILY);
        memberSvc.insertCreateDateTime(member, memberSvc);
        member.setMemberSvc(memberSvc);

        return member;
    }

}
