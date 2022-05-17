package com.springboot.GreenCommute.service;

import com.springboot.GreenCommute.entities.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    public List<Job> getAllJobs();

    public Optional<Job> getJobById(int jobId);

    public void addJob(Job job);

    public void deleteJob(int jobId);

    public List<Job> getJobsByLocation(String location);
}

