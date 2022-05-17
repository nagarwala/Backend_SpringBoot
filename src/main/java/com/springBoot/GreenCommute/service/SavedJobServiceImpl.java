package com.springBoot.GreenCommute.service;

import com.springBoot.GreenCommute.dto.JobDto;
import com.springBoot.GreenCommute.entities.User;
import com.springBoot.GreenCommute.entities.SavedJob;
import com.springBoot.GreenCommute.exception.DataNotFoundException;
import com.springBoot.GreenCommute.helper.Helper;
import com.springBoot.GreenCommute.mapper.JobMapper;
import com.springBoot.GreenCommute.repositories.SavedJobRepository;
import com.springBoot.GreenCommute.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SavedJobServiceImpl implements SavedJobService {

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private SavedJobRepository savedJobRepository;

    @Autowired
    private Helper helper;

    @Override
    public void addToSavedJob(int userId, int jobId) {

        Optional<User> tempUser = userService.getUserById(userId);
        Optional<Job> tempJob =  jobService.getJobById(jobId);
        SavedJob tempSavedJob = savedJobRepository.findByUserAndJobId(userId,jobId);
        if(tempSavedJob == null) {
            SavedJob savedJob = new SavedJob(new Timestamp(System.currentTimeMillis()), tempUser.get(), tempJob.get());
            savedJobRepository.save(savedJob);
        }
        else
            throw new DuplicateKeyException("The job is already present in the list");

    }

    @Override
    public void deleteFromSavedJob(int userId, int jobId) {

        SavedJob jobToDelete = savedJobRepository.findByUserAndJobId(userId,jobId);
        if(jobToDelete == null)
            throw new DataNotFoundException("User does not have a job saved with id: " + jobId);
        savedJobRepository.delete(jobToDelete);
    }

    @Override
    public List<JobDto> getSavedJobsForUser(int userId) {
        Optional<User> user = userService.getUserById(userId);
        List<Job> jobList = new ArrayList<>();
        List<SavedJob> savedJobList = user.get().getSavedJobList();
        for(SavedJob job: savedJobList){
            jobList.add(job.getJob());
        }

        List<JobDto> jobDtoList = jobMapper.toJobDtoList(jobList);
        for(JobDto dto: jobDtoList){
            Job job = jobService.getJobById(dto.getJobId()).get();
            List<String> commuteOptions = helper.getCommuteOption(job);
            dto.setCommuteOptions(commuteOptions);
        }
        return jobDtoList;
    }
}

