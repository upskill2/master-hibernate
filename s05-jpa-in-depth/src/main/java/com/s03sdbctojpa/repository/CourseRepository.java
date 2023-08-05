package com.s03sdbctojpa.repository;

import com.s03sdbctojpa.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class CourseRepository {

    @Autowired
    EntityManager entityManager;

    public Course findById(long id) {
        return entityManager.find(Course.class, id);

    }

    @Transactional
    public void deleteById(long id) {
        Course course = findById(id);
        if (course != null) {
            entityManager.remove(course);
        }
    }

    @Transactional
    public void save(Course course) {
       entityManager.persist(course);
       course.setName("TRANSACTIONAL");
    }

    @Transactional
    public Course update(Course course) {
        return entityManager.merge(course);
    }

    public List<Course> findAll(){
               return entityManager.createQuery("Select c from Course c", Course.class).getResultList();
    }




}
