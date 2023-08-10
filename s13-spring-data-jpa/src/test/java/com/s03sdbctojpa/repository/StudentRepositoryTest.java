package com.s03sdbctojpa.repository;


import com.s13.SpringDataJpa;

import com.s13.entity.Student;
import com.s13.entity.Workstation;
import com.s13.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;


@SpringBootTest (classes = SpringDataJpa.class)
@RunWith (SpringRunner.class)
@Slf4j
@ActiveProfiles ("test")
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void testCourseEntity () {
        Student student = studentRepository.findById (1L).orElse (new Student ());
        assertEquals ("Microservices in 100 Steps", Set.of (student.getCourses ()).stream ().toList ().get (0).stream ().findFirst ().get ().getName ());
    }

    @Test
    void testDeletedColumn(){
        Student student = new Student ("NEw", "A1234657", "some@email");
        Student student1 = new Student ("DFGGF", "TYU", "com");
        Student student2 = new Student ("G", "kl", "!FCgajh");

        studentRepository.saveAll (List.of (student, student1, student2));
        studentRepository.deleteById (2L);

        assertNull (studentRepository.findById (2L).orElse (null));
    }

    @Test
    void differentMethods () {
        Student student = new Student ("NEw", "A1234657", "");
        studentRepository.save (student);
        student.setEmail ("bla");
        studentRepository.save (student);
    }

    @Test
    void sorting () {
        Sort sort = Sort.by (Sort.Direction.DESC, "firstName");

        studentRepository.findAll (sort).forEach (s -> log.info (s.getFirstName ()));
    }

    @Test
    void testFindById () {
        Student studentDto = studentRepository.findById (1L).orElse (new Student ());
        assertEquals ("Jack", studentDto.getFirstName ());
    }

    @Test
    void pagination () {
        PageRequest pageRequest = PageRequest.of (0, 3);
        Page<Student> firstPage = studentRepository.findAll (pageRequest);
        firstPage.forEach (s -> log.info (s.getFirstName ()));

        Pageable secondPage = firstPage.nextPageable ();
    }

    @Test
    void testFindByName () {
        List<Student> list = studentRepository.findByFirstNameOrderByLastNameDesc ("John");
        assertThat (list.size (), greaterThan (0));
    }

    @Test
    void testQueries () {
        Workstation workstation = new Workstation ("Workstation 1", "Workstation 1", "Workstation 1");
        studentRepository.findById (1L).ifPresent (s -> {
            s.setWorkstation (workstation);
            studentRepository.save (s);

        });
        assertEquals (studentRepository.findByLikeName ().size (), studentRepository.findByLikeNameNative ().size ());
    }

    @Test
    void testNoCache () {
        log.info ("First time -> {}", studentRepository.findById (1L).get ().getFirstName ());
        log.info ("Second time -> {}", studentRepository.findById (1L).get ().getFirstName ());
    }

    @Test
    @Transactional
    void testCacheFirstLevel () {
        log.info ("First time -> {}", studentRepository.findById (1L).get ().getFirstName ());
        log.info ("Second time -> {}", studentRepository.findById (1L).get ().getFirstName ());
    }


}