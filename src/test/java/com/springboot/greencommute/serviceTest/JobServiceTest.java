package com.springboot.greencommute.serviceTest;

import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.repositories.JobRepository;
import com.springboot.greencommute.service.JobService;
import com.springboot.greencommute.service.JobServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
 class JobServiceTest {

   @Mock
   private JobService jobService;

   @Mock
   private JobRepository jobRepository;

   @BeforeEach
   void initUseCase(){
      jobService = new JobServiceImpl(jobRepository);
   }

   @Test
   void getJobByIdTest() {
      Job job = new Job(1,"dev","Hyderabad",null,null);
      Optional<Job> jobsOptional = Optional.of(job);
      Mockito.when(jobRepository.findById(1)).thenReturn(jobsOptional);
      Assertions.assertEquals(jobsOptional, jobService.getJobById(1));
      Mockito.verify(jobRepository).findById(1);
   }

   @Test
   void getAllJobsTest() {
      List<Job> jobsList = new ArrayList<>();
      Job job1 = new Job(1,"dev","Hyderabad",null,null);
      Job job2 = new Job(2,"dev","Kochi",null,null);
      jobsList.add(job1);
      jobsList.add(job2);
      Mockito.when(jobRepository.findAll()).thenReturn(jobsList);
      Assertions.assertEquals(jobsList, jobService.getAllJobs());
      Mockito.verify(jobRepository).findAll();
   }

   @Test
   void getJobsSearchByLocationTest() {
      List<Job> jobsList = new ArrayList<>();
      Job job = new Job(1,"dev","Hyderabad",null,null);
      jobsList.add(job);
      Mockito.when(jobRepository.getJobByLocation("Hyderabad")).thenReturn(jobsList);
      Assertions.assertEquals(jobsList, jobService.getJobsByLocation("Hyderabad"));
      Mockito.verify(jobRepository).getJobByLocation("Hyderabad");
   }
}
