package com.marketProject.domain.jpa.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "member_svc")
public class MemberSvc extends BaseEntity{
    @Id @Column(name = "member_id")
    @OneToOne
    private Long id;
    private LocalDate updatePwdDate;
    private LocalDate updateGradeDate;
    private int payCount;
    private Long payAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getUpdatePwdDate() {
        return updatePwdDate;
    }

    public void setUpdatePwdDate(LocalDate updatePwdDate) {
        this.updatePwdDate = updatePwdDate;
    }

    public LocalDate getUpdateGradeDate() {
        return updateGradeDate;
    }

    public void setUpdateGradeDate(LocalDate updateGradeDate) {
        this.updateGradeDate = updateGradeDate;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }
}
