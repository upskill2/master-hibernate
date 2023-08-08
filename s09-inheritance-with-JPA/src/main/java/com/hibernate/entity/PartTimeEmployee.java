package com.hibernate.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PartTimeEmployee extends Employee {

    private double hourlyWage;

    public PartTimeEmployee(String name, double hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

}
