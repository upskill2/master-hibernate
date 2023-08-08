package com.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FullTimeEmployee extends Employee {

    private double salary;

    public FullTimeEmployee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

}
