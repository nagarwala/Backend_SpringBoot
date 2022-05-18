package com.springboot.greencommute.entitiesTest;

import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.entities.SavedJob;
import com.springboot.greencommute.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class SavedJobEntityTest {

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    User user = new User(1,"test1",null);
    Job job = new Job(1,"test","us",null,null);

    SavedJob savedJob = new SavedJob(timestamp,user,job);

    @Test
    public void savedJobTest(){
        Assertions.assertEquals(timestamp,savedJob.getJobsSavedTime());
        Assertions.assertEquals(user,savedJob.getUser());
        Assertions.assertEquals(job,savedJob.getJob());
        Assertions.assertEquals(0,savedJob.getJobSavedId());

        savedJob = new SavedJob();
        savedJob.setJobSavedId(1);
        savedJob.setJob(job);
        savedJob.setUser(user);
        savedJob.setJobsSavedTime(timestamp);

        Assertions.assertEquals(timestamp,savedJob.getJobsSavedTime());
        Assertions.assertEquals(user,savedJob.getUser());
        Assertions.assertEquals(job,savedJob.getJob());
        Assertions.assertEquals(1,savedJob.getJobSavedId());

    }
}
