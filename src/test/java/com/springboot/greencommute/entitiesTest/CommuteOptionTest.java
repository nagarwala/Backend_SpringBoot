package com.springboot.greencommute.entitiesTest;

import com.springboot.greencommute.entities.CommuteOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommuteOptionTest {

    CommuteOption commuteOption = new CommuteOption(1,"bus",null);

    @Test
    public void CommuteTest(){
        Assertions.assertEquals(1,commuteOption.getCommuteId());
        Assertions.assertEquals("bus",commuteOption.getCommuteName());
        Assertions.assertNull(commuteOption.getJobList());

        commuteOption = new CommuteOption();
        commuteOption.setCommuteId(2);
        commuteOption.setCommuteName("car");
        commuteOption.setJobList(null);

        Assertions.assertEquals(2,commuteOption.getCommuteId());
        Assertions.assertEquals("car",commuteOption.getCommuteName());
        Assertions.assertNull(commuteOption.getJobList());

    }
}
