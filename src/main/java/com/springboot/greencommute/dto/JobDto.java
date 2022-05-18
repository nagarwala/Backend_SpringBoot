package com.springboot.greencommute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {

    private int jobId;

    private String jobTitle;

    private String jobLocation;

    private List<String> commuteOptions;

}

