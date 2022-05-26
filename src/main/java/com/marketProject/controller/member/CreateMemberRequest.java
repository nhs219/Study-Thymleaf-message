package com.marketProject.controller.member;

import com.marketProject.jpa.entity.Address;
import com.marketProject.jpa.entity.Member;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateMemberRequest {

    @NotEmpty @Email
    private String email;
    @NotEmpty
    @Size(min = 8)
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    @Size(min = 9, max = 11)
    private String phone;
    @NotEmpty
    private String street;
    @NotEmpty
    private String zipcode;
    private String detailAddress;

    public Member setMember() {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setName(name);
        member.setPhone(phone);
        member.setAddress(new Address(zipcode, street, detailAddress));

        return member;
    }
}
