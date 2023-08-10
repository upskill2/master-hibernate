package com.s13;

import com.s13.entity.Student;
import com.s13.repository.StudentDaoJPA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class SpringDataJpa {

    public static void main (String[] args) {
        SpringApplication.run (SpringDataJpa.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner (StudentDaoJPA studentDaoJPA) {
        return args -> {
            log.info ("Inserting -> {}", studentDaoJPA.
                    save (new Student ("John", "Doe", "co,")));
            log.info ("Student id 10001 -> {}", studentDaoJPA.findById (1L));
            log.info ("All users 1 -> {}", studentDaoJPA.findAll ());
        };
    }
}

