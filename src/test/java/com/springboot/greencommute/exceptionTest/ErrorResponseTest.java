package com.springboot.greencommute.exceptionTest;

import com.springboot.greencommute.exception.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@SpringBootTest
public class ErrorResponseTest {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),"Incorrect", timestamp.getTime());

    @Test
    void errorTest(){
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(),errorResponse.getStatus());
        Assertions.assertEquals("Incorrect",errorResponse.getMessage());
        Assertions.assertEquals(timestamp.getTime(),errorResponse.getTimeStamp());

        errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage("Incorrect");
        errorResponse.setTimeStamp(timestamp.getTime());

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(),errorResponse.getStatus());
        Assertions.assertEquals("Incorrect",errorResponse.getMessage());
        Assertions.assertEquals(timestamp.getTime(),errorResponse.getTimeStamp());

    }
}
