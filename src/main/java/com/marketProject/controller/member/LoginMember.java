package com.marketProject.controller.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
public class LoginMember {

    @NotEmpty @Email
    String email;

    @NotEmpty
    String password;
}
