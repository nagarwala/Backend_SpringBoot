package com.springboot.greencommute.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "jobs_saved")
public class SavedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobs_saved_id")
    private int jobSavedId;

    @Column(name = "jobs_saved_time")
    private Timestamp jobsSavedTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "jobs_id")
    @JsonBackReference
    private Job job;

    public SavedJob(Timestamp jobsSavedTime, User user, Job job) {
        this.jobsSavedTime = jobsSavedTime;
        this.user = user;
        this.job = job;
    }
}
