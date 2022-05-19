package com.springboot.greencommute.dtoTest;

import com.springboot.greencommute.dto.ResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@SpringBootTest
 class ResponseDtoTest {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    
    ResponseDto responseDto = new ResponseDto(HttpStatus.OK.value(), "Correct", timestamp.getTime());
    
    @Test
    void ResponseTest(){
        Assertions.assertEquals(HttpStatus.OK.value(),responseDto.getStatus());
        Assertions.assertEquals("Correct",responseDto.getMessage());
        Assertions.assertEquals(timestamp.getTime(),responseDto.getTimestamp());

        responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK.value());
        responseDto.setMessage("Correct");
        responseDto.setTimestamp(timestamp.getTime());

        Assertions.assertEquals(HttpStatus.OK.value(),responseDto.getStatus());
        Assertions.assertEquals("Correct",responseDto.getMessage());
        Assertions.assertEquals(timestamp.getTime(),responseDto.getTimestamp());
    }
}
