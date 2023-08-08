package com.hibernate.dto;

import com.hibernate.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentWrapper {

    public StudentDto toStudentDto(Student student) {
        return StudentDto.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .passportNumber(student.getPassport().getNumber())
                .build();
    }

}
