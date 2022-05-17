package com.springboot.GreenCommute.controllers;

import com.springboot.GreenCommute.dto.JobDto;
import com.springboot.GreenCommute.entities.Job;
import com.springboot.GreenCommute.exception.DataNotFoundException;
import com.springboot.GreenCommute.helper.Helper;
import com.springboot.GreenCommute.mapper.JobMapper;
import com.springboot.GreenCommute.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private Helper helper;

    @GetMapping("/jobs")
    public List<JobDto> getAllJobs(@RequestParam (required = false) String location, @RequestParam(required = false) String[] skills) {
        List<Job> jobList = jobService.getAllJobs();
        List<JobDto> jobDtoList;
        if(location == null && skills == null) {
            jobDtoList =  jobMapper.toJobDtoList(jobList);
        }
        else if(skills == null) {
         List<Job> jobListByLocation =  jobService.getJobsByLocation(location);
         jobDtoList =  jobMapper.toJobDtoList(jobListByLocation);
        }
        else if(location == null){
            List<Job> jobListBySkills =  helper.getJobListFilteredBySkills(jobList,skills);
            jobDtoList =  jobMapper.toJobDtoList(jobListBySkills);
        }
        else{
            List<Job> jobFilteredListByLocation = jobService.getJobsByLocation(location);
            List<Job> multipleFilteredJobList = helper.getJobListFilteredBySkills(jobFilteredListByLocation,skills);
            jobDtoList =  jobMapper.toJobDtoList(multipleFilteredJobList);
        }

        for(JobDto jobDto: jobDtoList){
            Job job = jobService.getJobById(jobDto.getJobId()).get();
            List<String> commute = helper.getCommuteOption(job);
            jobDto.setCommuteOptions(commute);
        }
        return jobDtoList;
    }

    @GetMapping("/jobs/{jobId}")
    public JobDto getJobById(@PathVariable int jobId){
        Optional<Job> job = jobService.getJobById(jobId);
        if(job.isEmpty())
            throw new DataNotFoundException("No user found with id: " + jobId);
        return jobMapper.toJobDto(job.get());
    }

    @PostMapping("/jobs")
    public String addJob(@RequestBody Job job){
        jobService.addJob(job);
        return ("Job added successfully");
    }

    @DeleteMapping("/jobs/{jobId}")
    public String deleteJobById(@PathVariable int jobId){
        jobService.deleteJob(jobId);
        return("Deleted job successfully with id: " + jobId);
    }

}

