package com.springboot.greencommute.mapperTest;

import com.springboot.greencommute.dto.JobDto;
import com.springboot.greencommute.dto.JobDto;
import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.mapper.JobMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class JobMapperTest {

    @Mock
    JobMapper jobMapper;

    @Test
    void entityToDtoMapperTest(){
        Job job = new Job(1,"dev","Pune",null,null);
        JobDto jobDto = new JobDto(1,"dev","Pune",null);
        Mockito.when(jobMapper.toJobDto(job)).thenReturn(jobDto);
        Assertions.assertEquals(jobDto,jobMapper.toJobDto(job));
        Mockito.verify(jobMapper).toJobDto(job);
    }

    @Test
    void entityToDtoListMapperTest(){
        List<Job> jobList = new ArrayList<>();
        List<JobDto> jobDtoList= new ArrayList<>();
        Job job = new Job(1,"dev","Pune",null,null);
        JobDto jobDto = new JobDto(1,"dev","Pune",null);
        jobList.add(job);
        jobDtoList.add(jobDto);
        Mockito.when(jobMapper.toJobDtoList(jobList)).thenReturn(jobDtoList);
        Assertions.assertEquals(jobDtoList,jobMapper.toJobDtoList(jobList));
        Mockito.verify(jobMapper).toJobDtoList(jobList);
    }
}
