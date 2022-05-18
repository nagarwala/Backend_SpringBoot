package com.springboot.greencommute.service;

import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Optional<Job> getJobById(int jobId) {
        return jobRepository.findById(jobId);
    }

    @Override
    public List<Job> getJobsByLocation(String location) {
        List<Job> jobList = jobRepository.getJobByLocation(location);
        if(jobList.isEmpty())
            throw new NoSuchElementException("No Job found with that location");

        return jobList;
    }
}
