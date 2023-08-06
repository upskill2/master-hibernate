package com.s03sdbctojpa;

import com.s03sdbctojpa.entity.Passport;
import com.s03sdbctojpa.entity.Student;
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
        Student student = new Student( "Jack", "Ma", "@alibaba.com");
        Passport passport = new Passport("E123456");

        student.setPassport(passport);
        studentRepository.save(student);

    }

}
