package com.s03sdbctojpa.repository;

import com.s03sdbctojpa.dto.StudentDto;
import com.s03sdbctojpa.dto.StudentWrapper;
import com.s03sdbctojpa.entity.Course;
import com.s03sdbctojpa.entity.Review;
import com.s03sdbctojpa.entity.Student;
import com.s03sdbctojpa.exceptions.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class StudentRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    StudentWrapper studentWrapper;

    public StudentDto findById(long id) {
        return studentWrapper.toStudentDto(entityManager.find(Student.class, id));
    }

    public Student studentById(long id) {
        return entityManager.find(Student.class, id);
    }

    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Transactional
    public Course updateCourse(Course course) {
        return entityManager.merge(course);
    }

    public Course findCourseForReview(long id) {
        Review review = entityManager.find(Review.class, id);
        return review.getCourse();
    }

    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Transactional
    public Student update(Student student) {
        return entityManager.merge(student);
    }

    @Transactional
    public int updateByCourseName(String updateFrom, String updateTo) {
        return entityManager.createQuery("update Course c set c.name = ?1 where c.name = ?2")
                .setParameter(1, updateTo)
                .setParameter(2, updateFrom)
                .executeUpdate();
    }

    public Course findCourseById(long id) {
        return entityManager.find(Course.class, id);
    }

    public List<Student> findAll() {
        return entityManager.createQuery("Select c from Student c", Student.class).getResultList();
    }

    public Student jpql_findById(long id) {
        return entityManager.createQuery("Select c from Course c where id = :id", Student.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Student NativeQuery(long id) {
        return (Student) entityManager.createNativeQuery("Select * from Course where id = :id", Student.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    public StudentDto findByName(String studentName) {
        List<Student> student = entityManager.createQuery("Select c from Student c where first_name = :name", Student.class)
                .setParameter("name", studentName)
                .getResultList();

        if (student.isEmpty()) {
            throw new StudentNotFoundException(studentName);
        }

        return StudentDto.builder()
                .firstName(student.get(0).getFirstName())
                .lastName(student.get(0).getLastName())
                .passportNumber(student.get(0).getPassport().getNumber())
                .build();
    }

    @Transactional
    public void deleteByName(String studentName) {
        entityManager.createQuery("delete from Student c where first_name = :name")
                .setParameter("name", studentName)
                .executeUpdate();
    }

}
