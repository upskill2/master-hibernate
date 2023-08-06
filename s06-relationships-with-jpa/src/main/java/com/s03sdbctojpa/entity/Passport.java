package com.s03sdbctojpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "passport")
@Data
@NoArgsConstructor
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "passport_number", nullable = false)
    private String number;

    public Passport(String number) {
        this.number = number;
    }

}
