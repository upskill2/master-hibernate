package com.hibernate.repository;

import com.hibernate.dto.StudentDto;
import com.hibernate.entity.Course;
import com.hibernate.entity.Passport;
import com.hibernate.entity.Review;
import com.hibernate.entity.Student;
import com.hibernate.exceptions.StudentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@Slf4j
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void addNewReviewsToCourse() {
        Review review = new Review("0", "Great Hands-on Stuff");
        Review review1 = new Review("0", "Hats Off");
        int courseId = 1;

        Course course = studentRepository.findCourseById(courseId);
        course.addReview(review);
        course.addReview(review1);

        studentRepository.updateCourse(course);
    }

    @Test
    void getReviewsForCourse() {
        int courseId = 1;

        Course course = studentRepository.findCourseById(courseId);
        course.getReviews().forEach(review -> log.info("{}", review));
    }

    @Test
    void getCourseForReview() {
        int reviewId = 1;
        log.info("Course name: {}", studentRepository.findCourseForReview(1).getName());
    }

    @Test
        //  @Transactional ()
        //  @Rollback(false)
    void testPersist() {

        Student student = new Student("BLU", "TY", "@354.com");
        Student student1 = new Student("TRE", "BUS", "@dxc.com");
        Passport passport = new Passport("P345468");
        Passport passport1 = new Passport("U12354");

        student.setPassport(passport);
        student1.setPassport(passport1);
        assertDoesNotThrow(() -> studentRepository.save(student));
        assertDoesNotThrow(() -> studentRepository.save(student1));
    }

    @Test
    void findById() {
        StudentDto student = studentRepository.findById(1L);
        assertEquals("Jack", student.getFirstName());
        assertEquals("E123456", student.getPassportNumber());
    }

    @Test
    void testDeleteByName() {
        studentRepository.deleteByName("TRE");
        assertThrows(StudentNotFoundException.class, () -> studentRepository.findByName("TRE"));
    }

    @Test
    void testUpdate() {
        long id = 1L;

        Student student = studentRepository.studentById(id);
        Passport passport = student.getPassport();

        student.setFirstName("UPDATE");
        student.setLastName("UPDATE");
        student.setEmail("update.com");
        passport.setNumber("UPDATE_123456");

        studentRepository.update(student);
        assertEquals("UPDATE", studentRepository.findById(id).getFirstName());
    }

}