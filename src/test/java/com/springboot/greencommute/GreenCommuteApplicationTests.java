package com.springboot.greencommute;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GreenCommuteApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertEquals(1,2-1);
    }
}
