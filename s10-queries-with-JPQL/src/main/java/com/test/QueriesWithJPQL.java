package com.test;


import com.hibernate.entity.Student;
import com.test.repository.StudentDaoNew;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan("com.hibernate.entity")
@Slf4j
public class QueriesWithJPQL  {

    public static void main(String[] args) {
        SpringApplication.run(QueriesWithJPQL.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner (StudentDaoNew studentDao) {
        return args -> {
            log.info ("Student id 10001 -> {}", studentDao.findById (1L));
            log.info ("All users 1 -> {}", studentDao.findAll ());
            log.info ("Inserting -> {}", studentDao.
                    save (new Student("John", "Doe", "co,")));
        };
    }
}
