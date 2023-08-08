package com.hibernate.repository;

import com.hibernate.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository <Student, Long> {

}
