package com.springBoot.GreenCommute.controllers;

import com.springBoot.GreenCommute.dto.JobDto;
import com.springBoot.GreenCommute.entities.Job;
import com.springBoot.GreenCommute.mapper.JobMapper;
import com.springBoot.GreenCommute.service.SavedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserSavedJobController {

    @Autowired
    private SavedJobService savedJobService;

    @Autowired
    private JobMapper jobMapper;

    @GetMapping("/users/{userId}/savedJobs")
    public List<JobDto> getSavedJobsForUser(@PathVariable int userId){
        List<Job> savedJobs =  savedJobService.getSavedJobsForUser(userId);
        return jobMapper.toJobDtoList(savedJobs);
    }

    @PostMapping("/users/{userId}/savedJobs")
    public String addJobToUser(@PathVariable int userId, @RequestParam int jobId){
        savedJobService.addToSavedJob(userId,jobId);
        return("Job with id: "+ jobId + " is added successfully to user :"+ userId);
    }

    @DeleteMapping("/users/{userId}/savedJobs")
    public String deleteJobFromUser(@PathVariable int userId, @RequestParam int jobId){
        savedJobService.deleteFromSavedJob(userId,jobId);
        return("Job with id: "+ jobId + " is deleted successfully from user :"+ userId);
    }

}

