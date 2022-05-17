package com.springBoot.GreenCommute.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springBoot.GreenCommute.dto.JobDto;
import com.springBoot.GreenCommute.dto.ResponseDto;
import com.springBoot.GreenCommute.entities.Job;
import com.springBoot.GreenCommute.mapper.JobMapper;
import com.springBoot.GreenCommute.service.SavedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserSavedJobController {

    @Autowired
    private SavedJobService savedJobService;

    @Autowired
    private JobMapper jobMapper;

    private ResponseDto responseDto;

    @GetMapping("/users/{userId}/savedJobs")
    public List<JobDto> getSavedJobsForUser(@PathVariable int userId){
         return savedJobService.getSavedJobsForUser(userId);
    }

    @PostMapping("/users/{userId}/savedJobs")
    public String addJobToUser(@PathVariable int userId, @RequestBody Map<String,Integer> reqPayload) throws JsonProcessingException {
        int jobId = reqPayload.get("jobId");
        if (savedJobService.addToSavedJob(userId,jobId)) {
            String res = "Job with id: " + jobId + " is added successfully to user :" + userId;
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setMessage(res);
        }
        else {
            responseDto.setMessage("Error");
            responseDto.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        responseDto.setTimestamp(System.currentTimeMillis());
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(responseDto);
    }

    @DeleteMapping("/users/{userId}/savedJobs")
    public String deleteJobFromUser(@PathVariable int userId, @RequestBody Map<String,Integer> reqPayload){
        int jobId = reqPayload.get("jobId");
        savedJobService.deleteFromSavedJob(userId,jobId);
        return("Job with id: "+ jobId + " is deleted successfully from user :"+ userId);
    }

}

