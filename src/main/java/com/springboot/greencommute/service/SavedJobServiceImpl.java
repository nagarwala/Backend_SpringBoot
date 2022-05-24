package com.springboot.greencommute.service;

import com.springboot.greencommute.exception.DataNotFoundException;
import com.springboot.greencommute.helper.Helper;
import com.springboot.greencommute.dto.JobDto;
import com.springboot.greencommute.entities.User;
import com.springboot.greencommute.entities.SavedJob;
import com.springboot.greencommute.mapper.JobMapper;
import com.springboot.greencommute.repositories.SavedJobRepository;
import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private JobService jobService;

    public SavedJobServiceImpl(SavedJobRepository savedJobRepository) {
        this.savedJobRepository = savedJobRepository;
    }

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private SavedJobRepository savedJobRepository;

    @Autowired
    private Helper helper;

    @Override
    public void addToSavedJob(int userId, int jobId) {

        Optional<User> tempUser = userRepository.findById(userId);
        Optional<Job> tempJob =  jobService.getJobById(jobId);
        if(tempUser.isPresent() && tempJob.isPresent()) {
            SavedJob tempSavedJob = savedJobRepository.findByUserAndJobId(userId, jobId);
            if (tempSavedJob == null) {
                SavedJob savedJob = new SavedJob(new Timestamp(System.currentTimeMillis()), tempUser.get(), tempJob.get());
                savedJobRepository.save(savedJob);
            } else
                throw new DuplicateKeyException("The job is already present in the list");
       }
        else {
           throw new DataNotFoundException("User or job does not exists");
      }
    }

    @Override
    public void deleteFromSavedJob(int userId, int jobId) {
            SavedJob jobToDelete = savedJobRepository.findByUserAndJobId(userId, jobId);
            if (jobToDelete == null)
                throw new DataNotFoundException("User does not have a job saved with id: " + jobId);
            savedJobRepository.delete(jobToDelete);
    }

    @Override
    public List<JobDto> getSavedJobsForUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            throw new DataNotFoundException("No user found with id: " + userId);

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

