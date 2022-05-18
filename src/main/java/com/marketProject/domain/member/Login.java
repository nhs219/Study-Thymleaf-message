package com.marketProject.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Login {

    @NotEmpty
    String userId;

    @NotEmpty
    String password;
}
