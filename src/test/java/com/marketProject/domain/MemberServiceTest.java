package com.marketProject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MemberServiceTest {

    @Autowired PasswordEncoder passwordEncoder;

    @Test
    void passwordEncoder() {
        //given
        String rawPassword = "test";

        //when
        String encodedPassword = passwordEncoder.encode(rawPassword);

        //then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> assertNotEquals(rawPassword, encodedPassword),
                () -> assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
        );
    }
}
