package com.s02springbootcourse;

import com.s02springbootcourse.domain.Course;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping ("/courses")
    public List<Course> retrieveAllCourses () {

        return Arrays.asList (
                new Course (1, "Learn AWs", "Learn AWS"),
                new Course (2, "Learn", "Learn AWS"),
                new Course (3, "Learn ", "LWS")
        );
    }

}
