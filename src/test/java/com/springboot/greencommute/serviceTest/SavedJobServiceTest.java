package com.springboot.greencommute.serviceTest;

import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.entities.SavedJob;
import com.springboot.greencommute.entities.User;
import com.springboot.greencommute.repositories.SavedJobRepository;
import com.springboot.greencommute.repositories.UserRepository;
import com.springboot.greencommute.service.SavedJobService;
import com.springboot.greencommute.service.SavedJobServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class SavedJobServiceTest {

    @Autowired
    @InjectMocks
    private SavedJobServiceImpl savedJobService;

    @MockBean
    SavedJobRepository savedJobRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void initUseCase(){
        savedJobService = new SavedJobServiceImpl(savedJobRepository);
    }

    @Test
    public void deleteSavedJobTest(){

        User user = new User(1,"test1",null);
        Job job = new Job(1,"dev","Pune",null,null);
        SavedJob savedJob = new SavedJob(new Timestamp(System.currentTimeMillis()), user, job);
        Mockito.when(savedJobRepository.findByUserAndJobId(1,1)).thenReturn(savedJob);
        doNothing().when(savedJobRepository).delete(savedJob);
        savedJobService.deleteFromSavedJob(1,1);
        Mockito.verify(savedJobRepository).delete(savedJob);
    }
}
