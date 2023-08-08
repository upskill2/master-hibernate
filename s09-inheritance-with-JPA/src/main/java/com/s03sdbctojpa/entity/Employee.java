package com.s03sdbctojpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //for single table
//@DiscriminatorColumn(name = "EmployeeType") //for single table
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //separate table for each class
//@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public abstract class Employee {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //for single table
    //@GeneratedValue(strategy = GenerationType.TABLE) //separate table for each class
    //@GeneratedValue(strategy = GenerationType.TABLE) //separate table in case of joins
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Employee(String name) {
        this.name = name;
    }

}
