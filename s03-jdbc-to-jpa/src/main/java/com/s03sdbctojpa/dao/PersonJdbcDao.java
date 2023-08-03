package com.s03sdbctojpa.dao;

import com.s03sdbctojpa.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
    }

    public Person findById(int id) {

        return jdbcTemplate.queryForObject("select * from person where id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public List<Person> findByNameAndLocation(String name, String location) {
        return jdbcTemplate.query("select * from person where name=? and location=?",
                new Object[]{name, location},
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("delete from person where id=?",
                id);
    }

    public int deleteByIdOrName(int id, String name) {
        return jdbcTemplate.update("delete from person where id=? or name=?",
                id, name);
    }

    public int insertPerson(Person person) {
        return jdbcTemplate.update("INSERT INTO person" +
                        "(" +
                        "id," +
                        "name," +
                        "location," +
                        "birth_date)" +
                        "VALUES" +
                        "(" +
                        "?," +
                        "?," +
                        "?," +
                        "?" +
                        ");",
               person.getId(), person.getName(), person.getLocation(), person.getBirthDate());
    }

    public int updatePerson(Person person) {
        return jdbcTemplate.update("UPDATE person set " +
                        "name=?," +
                        "location=?," +
                        "birth_date=?" +
                        "WHERE id=?;",
                person.getName(), person.getLocation(), person.getBirthDate(), person.getId());
    }

}
