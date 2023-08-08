package com.hibernate.repository;


import com.hibernate.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = com.hibernate.Jdbc.class)
@RunWith (org.springframework.test.context.junit4.SpringRunner.class)
@Slf4j
class StudentRepositoryTest {

    @Autowired
    StudentDao studentDao;

   @Test
    void testFindById () {
        Student studentDto = studentDao.findById (2L).orElse (new Student ());
        assertEquals ("John", studentDto.getFirstName ());
    }
}