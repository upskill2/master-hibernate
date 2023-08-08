package com.hibernate.repository;

import com.hibernate.entity.Employee;
import com.hibernate.entity.FullTimeEmployee;
import com.hibernate.entity.PartTimeEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public long insert(Employee employee) {
        entityManager.persist(employee);
        return employee.getId();
    }

    public List<Employee> retrieveAllEmployees() {
        return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    public List<PartTimeEmployee> retrievePartTimeEmployees() {
        return entityManager.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> retrieveFullTimeEmployees() {
        return entityManager.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }

}
