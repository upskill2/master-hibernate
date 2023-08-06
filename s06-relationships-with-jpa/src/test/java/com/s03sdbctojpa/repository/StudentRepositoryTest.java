package com.s03sdbctojpa.repository;

import com.s03sdbctojpa.dto.StudentDto;
import com.s03sdbctojpa.entity.Passport;
import com.s03sdbctojpa.entity.Student;
import com.s03sdbctojpa.exceptions.StudentNotFoundException;
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
        Long id = 1L;

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