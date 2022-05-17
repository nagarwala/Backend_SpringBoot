package com.springboot.GreenCommute.helper;

import com.springboot.GreenCommute.entities.CommuteOption;
import com.springboot.GreenCommute.entities.Job;
import com.springboot.GreenCommute.entities.Skill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Helper {

   public List<String> getCommuteOption(Job job){
        List<CommuteOption> commuteOptionList = job.getCommuteOptionList();
        List<String> commuteOptions = new ArrayList<>();
        for(CommuteOption option: commuteOptionList){
            commuteOptions.add(option.getCommuteName());
        }
        return commuteOptions;
    }

   public List<String> getSkillNames(List<Skill> skillList){
        List<String> jobHasSkills = new ArrayList<>();
        for(Skill skill: skillList){
            jobHasSkills.add(skill.getSkillName());
        }
        return jobHasSkills;
    }

    public List<Job> getJobListFilteredBySkills(List<Job>jobList, String[] skills){
         return jobList.stream().filter(job -> {
            List<Skill> skillList = job.getSkillList();
            List<String> skillNames = getSkillNames(skillList);
            for(String skillName: skills){
                if(skillNames.indexOf(skillName) != -1)
                    return true;
            }
            return false;
        }).collect(Collectors.toList());
    }
}
