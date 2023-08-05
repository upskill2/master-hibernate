package com.s03sdbctojpa;

import com.s03sdbctojpa.entity.Course;
import com.s03sdbctojpa.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
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
        log.info("Save/Update -> {}", CourseRepository.update(new Course(allCourses.size()-1L,"UPDATE")));

    }



}
