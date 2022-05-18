package com.springboot.greencommute.serviceTest;

import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.service.JobService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
 class JobServiceTest {

    @Mock
    private JobService jobService;

    @Test
     void getAllJobTest(){
        List<Job> jobList = Collections.emptyList();
        List<Job> testJobs = jobService.getAllJobs();
        Mockito.when(jobService.getAllJobs()).thenReturn(jobList);
        Assertions.assertEquals(jobList,testJobs);
        Mockito.verify(jobService).getAllJobs();
    }
    @Test
     void getJobsByIdTest(){
        int id = 1;
        Job job = new Job(1,"Developer","Mumbai",null,null);
        Optional<Job> jobOptional = Optional.of(job);
        Mockito.when(jobService.getJobById(id)).thenReturn(jobOptional);
        Assertions.assertEquals(jobOptional,jobService.getJobById(id));
        Mockito.verify(jobService).getJobById(id);
    }
}
