package com.springboot.GreenCommute.repositories;

import com.springboot.GreenCommute.entities.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedJobRepository extends JpaRepository<SavedJob,Integer> {

    @Query(value = "select * from jobs_saved where user_id=?1 and jobs_id=?2",nativeQuery = true)
    SavedJob findByUserAndJobId(int userId, int jobId);
}

