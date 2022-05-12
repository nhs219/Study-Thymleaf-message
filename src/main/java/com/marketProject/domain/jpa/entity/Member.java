package com.marketProject.domain.jpa.entity;

import com.marketProject.domain.member.Grade;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "member")
public class Member {
    @Id @Column(name = "member_id")
    @OneToOne(fetch = FetchType.EAGER) // 실무에서는 즉시로딩 지양. 지연로딩을 쓰는 것이 좋다. 작은 프로젝트는 써도 됨.
    @JoinColumn(name = "member_id")
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
