package com.s03sdbctojpa.repository;

import com.s03sdbctojpa.entity.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Long> {
    List<Student> findAll ();
}
