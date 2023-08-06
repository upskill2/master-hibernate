package com.s03sdbctojpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rating")
    private String rating;

    @Column(name = "description")
    private String description;

    public Description(String description) {
        this.rating = rating;
        this.description = description;
    }

}
