package com.marketProject.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "member_svc")
@Getter @Setter
public class MemberSvc extends BaseEntity{

    @Id @Column(name = "member_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate updatePwdDate;
    private LocalDate updateGradeDate;
    private int payCount;
    private Long payAmount;

    public MemberSvc insertCreateDateTime(Member member, MemberSvc memberSvc) {
        memberSvc.setId(member.getId());
        memberSvc.setCreateDatetime(LocalDateTime.now());
        memberSvc.setUpdateGradeDate(LocalDate.now());
        memberSvc.setUpdatePwdDate(LocalDate.now());
        return memberSvc;
    }

}
