package com.s03sdbctojpa;

import com.s03sdbctojpa.entity.Course;
import com.s03sdbctojpa.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class JpaInDepthApplication implements CommandLineRunner {

    @Autowired
    private CourseRepository CourseRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaInDepthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //find all
        List<Course> allCourses = CourseRepository.findAll();
        log.info("Find all -> {}", allCourses);

        //find by id
        log.info("Find by Id -> {}", CourseRepository.findById(allCourses.get(0).getId()));

        //delete by id
        log.info("Delete by id -> ");
        CourseRepository.deleteById(allCourses.get(1).getId());

        //update
        log.info("Save/Update -> {}", CourseRepository.updateByCourseName("French", "UPDATE_COMMAND_LINE_RUNNER"));

    }



}
