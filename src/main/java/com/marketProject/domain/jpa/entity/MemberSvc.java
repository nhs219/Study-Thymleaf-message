package com.marketProject.domain.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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



}
