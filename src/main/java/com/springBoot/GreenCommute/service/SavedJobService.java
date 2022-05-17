package com.springBoot.GreenCommute.service;

import com.springBoot.GreenCommute.dto.JobDto;
import com.springBoot.GreenCommute.entities.Job;

import java.util.List;

public interface SavedJobService {

    boolean addToSavedJob(int userId, int jobId);

    void deleteFromSavedJob(int userId, int jobId);

    List<JobDto> getSavedJobsForUser(int userId);
}

