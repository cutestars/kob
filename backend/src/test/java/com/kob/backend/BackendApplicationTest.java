package com.kob.backend;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class BackendApplicationTest {
    @Test
    void contextLoads(){
        PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("pyxc"));
        System.out.println(passwordEncoder.encode("pb"));
    }

}