package com.marketProject.controller.member;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UpdateMemberRequest {
    private String name;
    @Size(min = 9, max = 11)
    private String phone;
    private String zipcode;
    private String street;
    private String detailAddress;
    @Size(min = 8)
    private String password;
}
