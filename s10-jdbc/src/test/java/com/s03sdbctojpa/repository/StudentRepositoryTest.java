package com.s03sdbctojpa.repository;


import com.s03sdbctojpa.entity.Student;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(classes = com.s03sdbctojpa.Jdbc.class)
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