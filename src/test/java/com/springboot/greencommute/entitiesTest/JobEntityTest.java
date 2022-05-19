package com.springboot.greencommute.entitiesTest;

import com.springboot.greencommute.entities.Job;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class JobEntityTest {

    Job job = new Job(1,"test","us",null,null);

    @Test
     void jobTest(){
        Assertions.assertEquals(1, job.getJobId());
        Assertions.assertEquals("test", job.getJobTitle());
        Assertions.assertEquals("us",job.getJobLocation());
        Assertions.assertNull(job.getSkillList());
        Assertions.assertNull(job.getCommuteOptionList());

        job = new Job();
        job.setJobId(2);
        job.setJobTitle("newTest");
        job.setJobLocation("Uk");
        job.setSkillList(null);
        job.setCommuteOptionList(null);

        Assertions.assertEquals(2, job.getJobId());
        Assertions.assertEquals("newTest", job.getJobTitle());
        Assertions.assertEquals("Uk",job.getJobLocation());
        Assertions.assertNull(job.getSkillList());
        Assertions.assertNull(job.getCommuteOptionList());

    }
}
