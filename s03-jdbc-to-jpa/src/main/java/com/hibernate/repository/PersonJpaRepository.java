package com.hibernate.repository;

import com.hibernate.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person updatePerson(Person person) {
        return entityManager.merge(person);
    }

    public Person insertPerson(Person person) {
        return entityManager.merge(person);
    }

    public List<Person> findAll() {
        return entityManager.createNamedQuery("find_all_persons", Person.class).getResultList();
    }

    public void deleteById(int id) {
        Person person = findById(id);
        entityManager.remove(person);
    }

}
