package com.example.userresource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserResourceApplicationTests {

    @Test
    void contextLoads() {
        int dummy = 1;
        assertEquals(1, dummy);
    }
}
