package com.springboot.greencommute.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name="commute_option")
public class CommuteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commute_id")
    private int commuteId;

    @Column(name="commute_name")
    private String commuteName;

    @ManyToMany(mappedBy = "commuteOptionList", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Job> jobList;
}
