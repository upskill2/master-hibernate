package com.s03sdbctojpa;

import com.s03sdbctojpa.entity.*;
import com.s03sdbctojpa.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RelationshipsWithJPA implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(RelationshipsWithJPA.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student student = new Student("Jack", "Ma", "@alibaba.com");
        Passport passport = new Passport("E123456");
        Workstation workstation = new Workstation("Dell", "Latitude 5400", "Windows 10");

        Review review = new Review("Great Course", "5");
        Review review1 = new Review("Hats Off", "3");
        Review review2 = new Review("Wonderful", "1");

        Course course = new Course("Microservices in 100 Steps");
        course.addReview(review);
        course.addReview(review1);
        course.addReview(review2);

        Review review3 = new Review("Great Course", "5");
        Course course1 = new Course("Angular in 100 Steps");
        course1.getReviews().add(review3);

        Course course2 = new Course("React in 100 Steps");

        student.setPassport(passport);
        student.setWorkstation(workstation);
        studentRepository.save(student);

        //save course
        studentRepository.save(course);
        studentRepository.save(course1);
        studentRepository.save(course2);

    }

}
