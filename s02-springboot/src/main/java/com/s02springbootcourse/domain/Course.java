package com.s02springbootcourse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    private long id;
    private String username;
    private String description;

}
