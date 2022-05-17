package com.springboot.GreenCommute.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.GreenCommute.dto.JobDto;
import com.springboot.GreenCommute.dto.ResponseDto;
import com.springboot.GreenCommute.mapper.JobMapper;
import com.springboot.GreenCommute.service.SavedJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UserSavedJobController {

    @Autowired
    private SavedJobService savedJobService;

    @Autowired
    private JobMapper jobMapper;


    @GetMapping("/users/{userId}/savedJobs")
    public List<JobDto> getSavedJobsForUser(@PathVariable int userId){
         return savedJobService.getSavedJobsForUser(userId);
    }

    @PostMapping("/users/{userId}/savedJobs")
    public ResponseDto addJobToUser(@PathVariable int userId, @RequestBody Map<String,Integer> reqPayload) throws JsonProcessingException {
        int jobId = reqPayload.get("jobId");
        ResponseDto responseDto = new ResponseDto();
        savedJobService.addToSavedJob(userId,jobId) ;
        String res = "Job with id: " + jobId + " is added successfully to user :" + userId;
        responseDto.setStatus(HttpStatus.OK.value());
        responseDto.setMessage(res);
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }

    @DeleteMapping("/users/{userId}/savedJobs")
    public ResponseDto deleteJobFromUser(@PathVariable int userId, @RequestBody Map<String,Integer> reqPayload){
        int jobId = reqPayload.get("jobId");
        savedJobService.deleteFromSavedJob(userId,jobId);
        ResponseDto responseDto = new ResponseDto();
        String res =  "Job with id: "+ jobId + " is deleted successfully from user :"+ userId;
        responseDto.setStatus(HttpStatus.OK.value());
        responseDto.setMessage(res);
        responseDto.setTimestamp(System.currentTimeMillis());
        return responseDto;

    }

}

