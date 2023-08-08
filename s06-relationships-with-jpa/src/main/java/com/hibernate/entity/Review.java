package com.hibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rating")
    private String rating;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Course course;

    public Review(String description) {
        this.rating = rating;
        this.description = description;
    }

    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }

}
