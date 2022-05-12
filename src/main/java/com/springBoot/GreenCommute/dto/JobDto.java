package com.springBoot.GreenCommute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {

    private int jobId;

    private String jobTitle;

    private String jobLocation;

}

