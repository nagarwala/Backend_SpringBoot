package com.springboot.greencommute.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.greencommute.controllers.UserSavedJobController;
import com.springboot.greencommute.dto.JobDto;
import com.springboot.greencommute.dto.ResponseDto;
import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.entities.User;
import com.springboot.greencommute.mapper.JobMapper;
import com.springboot.greencommute.service.SavedJobServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserSavedJobControllerTest {

    @Mock
    SavedJobServiceImpl savedJobService;

    @InjectMocks
    UserSavedJobController userSavedJobController;

    @Mock
    JobMapper jobMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userSavedJobController).build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getSavedJobsForUserTest() throws Exception {
        Job job = new Job(1,"dev","Koch",null,null);
        List<Job> jobList=new ArrayList<>();
        jobList.add(job);
        List<JobDto> jobDtoList = jobMapper.toJobDtoList(jobList);

        Mockito.when(savedJobService.getSavedJobsForUser(1)).thenReturn(jobDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/savedJobs").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobList))).
                andDo(MockMvcResultHandlers.print());

        verify(savedJobService).getSavedJobsForUser(1);
        verify(savedJobService, times(1)).getSavedJobsForUser(1);
    }

    @Test
    void saveJobForUserTest() throws JsonProcessingException {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK.value());
        responseDto.setTimestamp(System.currentTimeMillis());
        responseDto.setMessage("Job with id: 6 is added successfully to user :1");
        Optional<User> user = Optional.of(new User(1, "test1", null));
        Optional<Job> job = Optional.of(new Job(1,"dev","Koch",null,null));

        Map<String,Integer> reqPayload = new HashMap<>();
        reqPayload.put("jobId",6);
        Mockito.doNothing().when(savedJobService).addToSavedJob(user.get().getUserId(), job.get().getJobId());
        savedJobService.addToSavedJob(user.get().getUserId(),job.get().getJobId());
        Assertions.assertEquals(responseDto.getMessage(),userSavedJobController.addJobToUser(1,reqPayload).getMessage());
        verify(savedJobService).addToSavedJob(user.get().getUserId(),job.get().getJobId());
        verify(savedJobService, times(1)).addToSavedJob(user.get().getUserId(),job.get().getJobId());
    }

    @Test
    void deleteJobForUserTest(){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK.value());
        responseDto.setTimestamp(System.currentTimeMillis());
        responseDto.setMessage("Job with id: 6 is deleted successfully from user :1");
        Optional<User> user = Optional.of(new User(1, "test1", null));
        Optional<Job> job = Optional.of(new Job(1,"dev","Koch",null,null));

        Map<String,Integer> reqPayload = new HashMap<>();
        reqPayload.put("jobId",6);
        Mockito.doNothing().when(savedJobService).deleteFromSavedJob(user.get().getUserId(), job.get().getJobId());
        savedJobService.deleteFromSavedJob(user.get().getUserId(),job.get().getJobId());
        Assertions.assertEquals(responseDto.getMessage(),userSavedJobController.deleteJobFromUser(1,reqPayload).getMessage());
        verify(savedJobService).deleteFromSavedJob(user.get().getUserId(),job.get().getJobId());
        verify(savedJobService, times(1)).deleteFromSavedJob(user.get().getUserId(),job.get().getJobId());
    }
}
