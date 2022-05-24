package com.springboot.greencommute.mapper;

import com.springboot.greencommute.dto.JobDto;
import com.springboot.greencommute.entities.Job;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-24T09:16:09+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Private Build)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public JobDto toJobDto(Job optionalJob) {
        if ( optionalJob == null ) {
            return null;
        }

        JobDto jobDto = new JobDto();

        jobDto.setJobId( optionalJob.getJobId() );
        jobDto.setJobTitle( optionalJob.getJobTitle() );
        jobDto.setJobLocation( optionalJob.getJobLocation() );

        return jobDto;
    }

    @Override
    public List<JobDto> toJobDtoList(List<Job> jobList) {
        if ( jobList == null ) {
            return null;
        }

        List<JobDto> list = new ArrayList<JobDto>( jobList.size() );
        for ( Job job : jobList ) {
            list.add( toJobDto( job ) );
        }

        return list;
    }
}
