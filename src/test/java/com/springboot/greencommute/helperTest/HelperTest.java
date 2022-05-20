package com.springboot.greencommute.helperTest;

import com.springboot.greencommute.entities.CommuteOption;
import com.springboot.greencommute.entities.Job;
import com.springboot.greencommute.entities.Skill;
import com.springboot.greencommute.helper.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HelperTest {

    @Mock
    Helper helper;

    @Test
    void getSkillNameTest(){
        List<Skill> skillList = new ArrayList<>();
        Skill skill = new Skill(1,"a",null);
        Skill skill2 = new Skill();
        skill2.setSkillId(2);
        skill2.setSkillName("b");
        skill2.setJobList(null);
        skillList.add(skill);
        skillList.add(skill2);
        List<String> skillNames = new ArrayList<>();
        skillNames.add(skill.getSkillName());
        skillNames.add(skill2.getSkillName());

        Mockito.when(helper.getSkillNames(skillList)).thenReturn(skillNames);
        Assertions.assertEquals(skillNames,helper.getSkillNames(skillList));
        Mockito.verify(helper).getSkillNames(skillList);

        skillNames = new ArrayList<>();
        List<Skill> skillsList1 = new ArrayList<>();
        Mockito.when(helper.getSkillNames(skillsList1)).thenReturn(skillNames);
        Assertions.assertEquals(skillNames,helper.getSkillNames(skillsList1));
        Mockito.verify(helper).getSkillNames(skillList);
    }

    @Test
    void getCommuteOptionTest(){
        List<CommuteOption> commuteOptionList = new ArrayList<>();
        CommuteOption commuteOption = new CommuteOption(1,"Bus",null);
        commuteOptionList.add(commuteOption);
        Job job = new Job(1,"dev","pune",null,commuteOptionList);
        List<String> commuteOptionNames = new ArrayList<>();
        commuteOptionNames.add("Bus");
        Mockito.when(helper.getCommuteOption(job)).thenReturn(commuteOptionNames);
        Assertions.assertEquals(commuteOptionNames,helper.getCommuteOption(job));
        Mockito.verify(helper).getCommuteOption(job);
    }

    @Test
    void jobSearchBySkills(){
        List<Job> jobsList = new ArrayList<>();
        List<Job> jobsList1 = new ArrayList<>();
        Job job1 = new Job(1,"dev","Hyderabad",null,null);
        Job job2 = new Job(2,"analyst","Pune",null,null);
        List<Skill> skillList = new ArrayList<>();
        Skill skill = new Skill(1,"C++",null);
        skillList.add(skill);
        job1.setSkillList(skillList);
        job2.setSkillList(skillList);
        jobsList.add(job1);
        jobsList.add(job2);

        String[] skillSearch = new String[]{"C++"};
        Mockito.when(helper.getJobListFilteredBySkills(jobsList,skillSearch)).thenReturn(jobsList);
        Assertions.assertEquals(jobsList,helper.getJobListFilteredBySkills(jobsList,skillSearch));
        Mockito.verify(helper).getJobListFilteredBySkills(jobsList,skillSearch);

        String[] skillSearch2 = new String[]{"Python"};
        Mockito.when(helper.getJobListFilteredBySkills(jobsList,skillSearch2)).thenReturn(jobsList1);
        Assertions.assertEquals(jobsList1,helper.getJobListFilteredBySkills(jobsList,skillSearch2));
        Mockito.verify(helper).getJobListFilteredBySkills(jobsList,skillSearch2);
    }
}
