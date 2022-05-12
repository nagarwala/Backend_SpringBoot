package com.springBoot.GreenCommute.controllers;

import com.springBoot.GreenCommute.dto.JobDto;
import com.springBoot.GreenCommute.entities.Job;
import com.springBoot.GreenCommute.entities.Skill;
import com.springBoot.GreenCommute.exception.DataNotFoundException;
import com.springBoot.GreenCommute.mapper.JobMapper;
import com.springBoot.GreenCommute.repositories.JobRepository;
import com.springBoot.GreenCommute.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    List<String> getSkillNames(List<Skill> skillList){
        List<String> jobHasSkills = new ArrayList<>();
        for(Skill skill: skillList){
            jobHasSkills.add(skill.getSkillName());
        }
        return jobHasSkills;
    }

    List<Job> getJobListFilteredBySkills(List<Job>jobList, String[] skills){
        List<Job> jobFilteredList = jobList.stream().filter(job -> {
            List<Skill> skillList = job.getSkillList();
            List<String> skillNames = getSkillNames(skillList);
            for(String skillName: skills){
                if(skillNames.indexOf(skillName) != -1)
                    return true;
            }
            return false;
        }).collect(Collectors.toList());
        return  jobFilteredList;
    }

    @GetMapping("/jobs")
    public List<JobDto> getAllJobs(@RequestParam (required = false) String location, @RequestParam(required = false) String[] skills) {
        List<Job> jobList = jobService.getAllJobs();
        if(location == null && skills == null)
            return jobMapper.toJobDtoList(jobList);
        else if(skills == null) {
         List<Job> jobListByLocation =  jobService.getJobsByLocation(location);
         return jobMapper.toJobDtoList(jobListByLocation);
        }
        else if(location == null){
            List<Job> jobListBySkills =  getJobListFilteredBySkills(jobList,skills);
            return jobMapper.toJobDtoList(jobListBySkills);
        }
        else{
            List<Job> jobFilteredListByLocation = jobService.getJobsByLocation(location);
            List<Job> multipleFilteredJobList = getJobListFilteredBySkills(jobFilteredListByLocation,skills);
            return jobMapper.toJobDtoList(multipleFilteredJobList);
        }
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

