package com.springBoot.GreenCommute.service;

import com.springBoot.GreenCommute.entities.Job;

import java.util.List;

public interface SavedJobService {

    void addToSavedJob(int userId, int jobId);

    void deleteFromSavedJob(int userId, int jobId);

    List<Job> getSavedJobsForUser(int userId);
}
