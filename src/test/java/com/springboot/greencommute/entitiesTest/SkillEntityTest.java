package com.springboot.greencommute.entitiesTest;

import com.springboot.greencommute.entities.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SkillEntityTest {

    Skill skill = new Skill(1,"swimming",null);

    @Test
    void SkillTest(){
        Assertions.assertEquals(1,skill.getSkillId());
        Assertions.assertEquals("swimming",skill.getSkillName());
        Assertions.assertNull(skill.getJobList());

        skill = new Skill();
        skill.setSkillId(2);
        skill.setSkillName("dancing");
        skill.setJobList(null);

        Assertions.assertEquals(2,skill.getSkillId());
        Assertions.assertEquals("dancing",skill.getSkillName());
        Assertions.assertNull(skill.getJobList());
    }
}
