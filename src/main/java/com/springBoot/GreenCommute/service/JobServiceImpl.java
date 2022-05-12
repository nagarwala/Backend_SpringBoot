package com.springBoot.GreenCommute.service;

import com.springBoot.GreenCommute.entities.Job;
import com.springBoot.GreenCommute.repositories.JobRepository;
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
    public void addJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void deleteJob(int jobId) {
        jobRepository.deleteById(jobId);
    }

    @Override
    public List<Job> getJobsByLocation(String location) {
        List<Job> jobList = jobRepository.getJobByLocation(location);
        if(jobList.isEmpty())
            throw new NoSuchElementException("No Job found with that location");

        return jobList;
    }
}
