package com.springboot.GreenCommute.service;
import com.springboot.GreenCommute.dto.JobDto;
import java.util.List;

public interface SavedJobService {

    void addToSavedJob(int userId, int jobId);

    void deleteFromSavedJob(int userId, int jobId);

    List<JobDto> getSavedJobsForUser(int userId);
}

