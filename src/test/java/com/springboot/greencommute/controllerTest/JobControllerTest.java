package com.springboot.greencommute.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.greencommute.controllers.JobController;
import com.springboot.greencommute.dto.JobDto;
import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.mapper.JobMapper;
import com.springboot.greencommute.service.JobService;
import com.springboot.greencommute.service.JobServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobControllerTest {

    @InjectMocks
   JobController jobController;

    @Mock
    JobMapper jobMapper;

    @Mock
    JobServiceImpl jobService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getJobByIdTest() throws Exception {
        Optional<Job> jobsOptional = Optional.of(new Job(1,"dev","Koch",null,null));
        JobDto jobDto = jobMapper.toJobDto(jobsOptional.get());
        when(jobService.getJobById(1)).thenReturn(jobsOptional);
        mockMvc.perform(MockMvcRequestBuilders.get("/jobs/1").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobDto))).
                andDo(MockMvcResultHandlers.print());
        verify(jobService).getJobById(1);
        verify(jobService,times(1)).getJobById(1);
    }
}
