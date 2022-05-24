package com.springboot.greencommute.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.greencommute.controllers.JobController;
import com.springboot.greencommute.dto.JobDto;
import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.entities.Skill;
import com.springboot.greencommute.helper.Helper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobControllerTest {

    @InjectMocks
   JobController jobController;

    @Mock
    Helper helper;

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

    @Test
    void getAllJobTestByFiltering() throws Exception {
     Job job = new Job(1,"dev","Koch",null,null);
        Skill skill = new Skill(1,"singing",null);
        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill);
        job.setSkillList(skillList);
        JobDto jobDto = jobMapper.toJobDto(job);
        Optional<Job> jobsOptional = Optional.of(job);
        List<Job> jobsList = new ArrayList<>();
        jobsList.add(job);
        List<JobDto> jobsDtoList = jobMapper.toJobDtoList(jobsList);
        when(jobService.getAllJobs()).thenReturn(jobsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/jobs").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobsDtoList))).
                andDo(MockMvcResultHandlers.print());
        verify(jobService).getAllJobs();
        verify(jobService,times(1)).getAllJobs();

        when(jobService.getJobsByLocation("Koch")).thenReturn(jobsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/jobs?location=Koch").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobsDtoList))).
                andDo(MockMvcResultHandlers.print());
        verify(jobService).getJobsByLocation("Koch");
        verify(jobService,times(1)).getJobsByLocation("Koch");

        String[] skillSearch = new String[]{"singing"};
        when(helper.getJobListFilteredBySkills(jobsList,skillSearch)).thenReturn(jobsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/jobs?skills=singing").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobsDtoList))).
                andDo(MockMvcResultHandlers.print());
        verify(helper).getJobListFilteredBySkills(jobsList,skillSearch);
        verify(helper,times(1)).getJobListFilteredBySkills(jobsList,skillSearch);

        when(helper.getJobListFilteredBySkills(jobsList,skillSearch)).thenReturn(jobsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/jobs?location=Koch&skills=singing").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobsDtoList))).
                andDo(MockMvcResultHandlers.print());
        verify(helper,times(2)).getJobListFilteredBySkills(jobsList,skillSearch);
    }
}
