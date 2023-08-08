package com.s03sdbctojpa;

import com.s03sdbctojpa.entity.Employee;
import com.s03sdbctojpa.entity.FullTimeEmployee;
import com.s03sdbctojpa.entity.PartTimeEmployee;
import com.s03sdbctojpa.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class InheritanceWithJPA implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(InheritanceWithJPA.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.insert(new FullTimeEmployee("Jack", 10000.25));
        employeeRepository.insert(new PartTimeEmployee("Jill", 25.99));

      //  log.info("All employees -> {}", employeeRepository.retrieveAllEmployees());
        log.info("Retrieve part time employees -> {}", employeeRepository.retrievePartTimeEmployees());
        log.info("Retrieve FULL time employees -> {}", employeeRepository.retrieveFullTimeEmployees());
    }

}
