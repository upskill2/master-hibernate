package com.s13;

import com.s13.entity.Address;
import com.s13.entity.Review;
import com.s13.entity.Student;
import com.s13.repository.ReviewRepository;
import com.s13.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Slf4j
public class SpringDataJpa {

    public static void main (String[] args) {
        SpringApplication.run (SpringDataJpa.class, args);
    }

    @Bean
    @Profile ("!test")
    public CommandLineRunner commandLineRunner (StudentRepository studentRepository, ReviewRepository reviewRepository) {
        return args -> {
            Address address = new Address ("Some Street", "Some City", "Some State");
            Student student = new Student ("John", "Doe", "co,");
            student.setAddress (address);
            log.info ("Inserting -> {}", studentRepository.
                    save (student));
            log.info ("Student id 10001 -> {}", studentRepository.findById (1L));
            log.info ("All users 1 -> {}", studentRepository.findAll ());

            //review qith Enum
            log.info ("Update 10001 -> {}", reviewRepository.save (new Review (Rating.ONE, "Great")));
        };
    }
}