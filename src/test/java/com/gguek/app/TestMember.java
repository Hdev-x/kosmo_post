package com.gguek.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TestMember {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void setPassword() throws Exception {
        String[] passwords = { "user", "1234", "users", "user2" };

        for (String pw : passwords) {
            String encoded = passwordEncoder.encode(pw);
            System.out.println(pw + "의 암호문: " + encoded);
            log.info("{} 의 암호문: {}", pw, encoded);
        }
    }

}
