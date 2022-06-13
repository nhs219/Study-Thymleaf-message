package com.marketProject.controller.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginMember  {

    @NotEmpty
    String userId;

    @NotEmpty
    String password;
}
