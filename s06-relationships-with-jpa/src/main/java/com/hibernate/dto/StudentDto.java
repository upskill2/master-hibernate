package com.hibernate.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class StudentDto {

    private String firstName;
    private String lastName;
    private String email;
    private String passportNumber;

}
