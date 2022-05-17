package com.springboot.GreenCommute.repositories;

import com.springboot.GreenCommute.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {

    @Query(value = "SELECT * from job where job_location=?1",nativeQuery = true)
    List<Job> getJobByLocation(String location);
}
