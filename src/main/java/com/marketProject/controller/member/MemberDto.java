package com.marketProject.controller.member;

import com.marketProject.jpa.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private String email;
    private Address address;
    private String phone;
}
