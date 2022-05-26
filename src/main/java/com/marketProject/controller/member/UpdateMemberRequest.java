package com.marketProject.controller.member;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UpdateMemberRequest {
    String name;
    @Size(min = 9, max = 11)
    String phone;
    String zipcode;
    String street;
    String detailAddress;
    @Size(min = 8)
    String password;
}
