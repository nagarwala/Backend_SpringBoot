package com.springboot.greencommute.dto;

import lombok.*;

import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {

    private int jobId;

    private String jobTitle;

    private String jobLocation;

    private List<String> commuteOptions;

}

