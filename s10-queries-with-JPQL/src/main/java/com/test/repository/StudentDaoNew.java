package com.test.repository;

import com.hibernate.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDaoNew extends JpaRepository<Student, Long> {
}
