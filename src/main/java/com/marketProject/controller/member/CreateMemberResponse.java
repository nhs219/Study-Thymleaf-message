package com.marketProject.controller.member;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateMemberResponse {
    private Long memberId;
    private String name;
    private String email;
    private LocalDateTime createDateTime;
}
