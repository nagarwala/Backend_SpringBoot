package com.springboot.greencommute.controllerTest;

import com.springboot.greencommute.controllers.JobController;
import com.springboot.greencommute.dto.JobDto;
import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.mapper.JobMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobControllerTest {

    @Mock
   JobController jobController;

    @Mock
    JobMapper jobMapper;

    Job job = new Job(1,"dev","Koch",null,null);

    @Test
    void getJobByIdTest(){
        int id = 1;
        JobDto jobDto = jobMapper.toJobDto(job);
        Mockito.when(jobController.getJobById(id)).thenReturn(jobDto);
        Assertions.assertEquals(jobDto,jobController.getJobById(id));
        Mockito.verify(jobController).getJobById(id);
    }
}
