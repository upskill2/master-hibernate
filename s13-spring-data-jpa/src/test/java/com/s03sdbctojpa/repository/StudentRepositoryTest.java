package com.s03sdbctojpa.repository;


import com.s13.SpringDataJpa;

import com.s13.entity.Course;
import com.s13.entity.Student;
import com.s13.entity.Workstation;
import com.s13.repository.StudentDaoJPA;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;


@SpringBootTest (classes = SpringDataJpa.class)
@RunWith (SpringRunner.class)
@Slf4j
class StudentRepositoryTest {

    @Autowired
    StudentDaoJPA studentDaoJPA;

    @Test
    void testCourseEntity () {
        Student student = studentDaoJPA.findById (1L).orElse (new Student ());
        assertEquals ("Microservices in 100 Steps", Set.of (student.getCourses ()).stream ().toList ().get (0).stream ().findFirst ().get ().getName ());
    }


    @Test
    void differentMethods () {
        Student student = new Student ("NEw", "A1234657", "");
        studentDaoJPA.save (student);
        student.setEmail ("bla");
        studentDaoJPA.save (student);
    }

    @Test
    void sorting () {
        Sort sort = Sort.by (Sort.Direction.DESC, "firstName");

        studentDaoJPA.findAll (sort).forEach (s -> log.info (s.getFirstName ()));
    }

    @Test
    void testFindById () {
        Student studentDto = studentDaoJPA.findById (1L).orElse (new Student ());
        assertEquals ("Jack", studentDto.getFirstName ());
    }

    @Test
    void pagination () {
        PageRequest pageRequest = PageRequest.of (0, 3);
        Page<Student> firstPage = studentDaoJPA.findAll (pageRequest);
        firstPage.forEach (s -> log.info (s.getFirstName ()));

        Pageable secondPage = firstPage.nextPageable ();
    }

    @Test
    void testFindByName () {
        List<Student> list = studentDaoJPA.findByFirstNameOrderByLastNameDesc ("John");
        assertThat (list.size (), greaterThan (0));
    }

    @Test
    void testQueries(){
        Workstation workstation = new Workstation ("Workstation 1", "Workstation 1", "Workstation 1");
        studentDaoJPA.findById (1L).ifPresent (s -> {
            s.setWorkstation (workstation);
            studentDaoJPA.save (s);

        });
        assertEquals (studentDaoJPA.findByLikeName ().size (), studentDaoJPA.findByLikeNameNative ().size ());
    }

}