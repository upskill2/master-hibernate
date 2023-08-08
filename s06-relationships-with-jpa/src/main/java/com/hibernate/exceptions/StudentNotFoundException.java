package com.hibernate.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String studentName) {
        super("Student not found with name: " + studentName);

    }

}
