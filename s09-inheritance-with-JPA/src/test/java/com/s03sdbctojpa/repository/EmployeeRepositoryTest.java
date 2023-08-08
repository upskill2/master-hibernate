package com.s03sdbctojpa.repository;

import com.s03sdbctojpa.entity.Employee;
import com.s03sdbctojpa.entity.FullTimeEmployee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@RunWith (org.springframework.test.context.junit4.SpringRunner.class)
class EmployeeRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void testRetrieveAllEmployees () {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder ();
        CriteriaQuery<FullTimeEmployee> query = cb.createQuery (FullTimeEmployee.class);
        Root<FullTimeEmployee> employeeRoot = query.from (FullTimeEmployee.class);

        TypedQuery<FullTimeEmployee> typedQuery = entityManager.createQuery (query.select (employeeRoot));

        log.info ("All employees -> {}", typedQuery.getResultList ());
    }

    @Test
    void testRetrieveAllEmployeesWithLike () {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder ();
        CriteriaQuery<FullTimeEmployee> query = cb.createQuery (FullTimeEmployee.class);
        Root<FullTimeEmployee> employeeRoot = query.from (FullTimeEmployee.class);
        Predicate like = cb.like (employeeRoot.get ("name"), "%J%");
            query.where (like);

        TypedQuery<FullTimeEmployee> typedQuery = entityManager.createQuery (query.select (employeeRoot));

        log.info ("All employees -> {}", typedQuery.getResultList ());
    }
}