package com.s03sdbctojpa;

import com.s03sdbctojpa.entity.*;
import com.s03sdbctojpa.repository.StudentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Jdbc {

    public static void main (String[] args) {
        SpringApplication.run (Jdbc.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner (StudentDao studentDao) {
        return args -> {
            log.info ("Student id 10001 -> {}", studentDao.findById (10001L));
            log.info ("All users 1 -> {}", studentDao.findAll ());
            log.info ("Inserting -> {}", studentDao.
                    save (new Student ("John", "Doe", "co,")));
        };
    }
}

