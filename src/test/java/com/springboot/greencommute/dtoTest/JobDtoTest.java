package com.springboot.greencommute.dtoTest;

import com.springboot.greencommute.dto.JobDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobDtoTest {

    JobDto jobDto = new JobDto(1,"Analyst","Cochin",null);

    @Test
    public void dtoEntityJobTest(){
        Assertions.assertEquals(1,jobDto.getJobId());
        Assertions.assertEquals("Analyst",jobDto.getJobTitle());
        Assertions.assertEquals("Cochin",jobDto.getJobLocation());
        Assertions.assertNull(jobDto.getCommuteOptions());

        jobDto = new JobDto();
        jobDto.setJobId(2);
        jobDto.setJobTitle("Tester");
        jobDto.setJobLocation("Delhi");
        jobDto.setCommuteOptions(null);

        Assertions.assertEquals(2,jobDto.getJobId());
        Assertions.assertEquals("Tester",jobDto.getJobTitle());
        Assertions.assertEquals("Delhi",jobDto.getJobLocation());
        Assertions.assertNull(jobDto.getCommuteOptions());



    }
}
