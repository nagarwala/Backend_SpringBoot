package com.springboot.greencommute.exceptionTest;


import com.springboot.greencommute.exception.DataNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataNotFoundExceptionTest {

    DataNotFoundException dataNotFoundException = new DataNotFoundException("No Data Found!");

    @Test
    void dataNotFoundExceptionTest() {
        Assertions.assertEquals("No Data Found!",dataNotFoundException.getMessage());
    }

    @Test
    void dataCauseExceptionTest(){
        dataNotFoundException = new DataNotFoundException("Data is empty",null);
        Assertions.assertEquals("Data is empty",dataNotFoundException.getMessage());
        Assertions.assertNull(dataNotFoundException.getCause());
    }
    @Test
    void causeExceptionTest(){
        dataNotFoundException = new DataNotFoundException((Throwable) null);
        Assertions.assertNull(dataNotFoundException.getCause());
    }
}
