package com.hibernate.repository;

import com.hibernate.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    }

    @Transactional
    public Course update(Course course) {
        return entityManager.merge(course);
    }

    @Transactional
    public int updateByCourseName(String updateFrom, String updateTo) {
        return entityManager.createQuery("update Course c set c.name = ?1 where c.name = ?2")
                .setParameter(1, updateTo)
                .setParameter(2, updateFrom)
                .executeUpdate();
    }

    public List<Course> findAll() {
        return entityManager.createQuery("Select c from Course c", Course.class).getResultList();
    }

    public List<Course> findAllNamedQuery() {
        return entityManager.createNamedQuery("query_get_all_courses", Course.class).getResultList();
    }


    public Course jpql_findById(long id) {
        return entityManager.createQuery("Select c from Course c where id = :id", Course.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Course NativeQuery(long id) {
        return (Course) entityManager.createNativeQuery("Select * from Course where id = :id", Course.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
