package com.s03sdbctojpa.repository;

import com.s03sdbctojpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    void findById() {
        long lastId = courseRepository.findAll().size() - 1;
        Course course = courseRepository.findById(lastId);
        assertNotNull(course.getName());
    }

    @Test
    @DirtiesContext
    void deleteById() {
        int counter = 0;
        for (int i = 1; i < 100; i++) {
            if (courseRepository.findById(i) != null) {
                courseRepository.deleteById(i);
                counter = i;
                break;
            }
        }

        assertNull(courseRepository.findById(counter));
    }

    @Test
    void testUpdate() {
        courseRepository.update(new Course(6, "UPDATE"));
    }

    @Test
    void testSave() {
        courseRepository.save(new Course("NEW_UPDATE"));
    }

    @Test
    void testUpdateByName() {
        courseRepository.updateByCourseName("NEW_UPDATE", "REU");
    }

    @Test
    void namedQuery() {
        assertThat(courseRepository.findAllNamedQuery().size(), greaterThan(0));
    }

}