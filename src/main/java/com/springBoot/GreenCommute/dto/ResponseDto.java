package com.springBoot.GreenCommute.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ResponseDto {

    private int status;
   private String message;
   private long Timestamp;

    @Override
    public String toString() {
        return "ResponseDto{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", Timestamp=" + Timestamp +
                '}';
    }
}
