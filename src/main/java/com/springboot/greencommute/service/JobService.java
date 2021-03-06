package com.springboot.greencommute.service;

import com.springboot.greencommute.entities.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    public List<Job> getAllJobs();

    public Optional<Job> getJobById(int jobId);

    public List<Job> getJobsByLocation(String location);
}

