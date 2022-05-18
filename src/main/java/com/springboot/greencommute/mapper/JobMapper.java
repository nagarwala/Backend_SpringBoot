package com.springboot.greencommute.mapper;

import com.springboot.greencommute.dto.JobDto;
import com.springboot.greencommute.entities.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Job.class, JobDto.class})
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Mapping(source = "jobId", target = "jobId")
    @Mapping(source = "jobTitle", target = "jobTitle")
    @Mapping(source = "jobLocation", target = "jobLocation")
    @Mapping(target = "commuteOptions", ignore = true)
    JobDto toJobDto(Job optionalJob);

    List<JobDto> toJobDtoList (List<Job> jobList);
}