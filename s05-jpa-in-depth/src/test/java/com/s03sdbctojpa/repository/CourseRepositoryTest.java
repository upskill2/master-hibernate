package com.s03sdbctojpa.repository;

import com.s03sdbctojpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    void findById() {
        long lastId = courseRepository.findAll().size()-1;
        Course course = courseRepository.findById(lastId);
        assertNotNull(course.getName());
    }

    @Test
    @DirtiesContext
    void deleteById() {
        int counter = 0;
        for (int i = 1; i < 100; i++) {
            if(courseRepository.findById(i)!=null){
                courseRepository.deleteById(i);
                counter = i;
                break;
            }
        }

        assertNull(courseRepository.findById(counter));
    }

    @Test
    void testUpdate(){
        courseRepository.update(new Course(9,"UPDATE"));
    }

    @Test
    void testSave(){
        courseRepository.save(new Course("UPDATE"));
    }

}