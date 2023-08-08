package com.s03sdbctojpa.entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table (name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "find_all_persons", query = "select p from Person p")
public class Person {

    @Id
    @GeneratedValue
    private int id;

    @Column (name = "name")
    private String name;

    @Column (name = "location")
    private String location;

    @Column (name = "birth_date")
    private Timestamp birthDate;


    public Person(String name, String location, Timestamp birthDate) {
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

}
