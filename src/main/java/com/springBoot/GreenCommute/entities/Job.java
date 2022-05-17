package com.springBoot.GreenCommute.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private int jobId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_location")
    private String jobLocation;

    @ManyToMany
    @JoinTable(name = "job_skillset", joinColumns = {@JoinColumn(name = "job_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    @JsonManagedReference
    private List<Skill> skillList;

    @ManyToMany
    @JoinTable(name = "job_has_commute",joinColumns = {@JoinColumn(name = "job_id")},
       inverseJoinColumns = {@JoinColumn(name = "commute_id")})
    @JsonManagedReference
    private List<CommuteOption> commuteOptionList;
}
