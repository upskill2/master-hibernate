package com.s13.entity;

import com.s13.Rating;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Course course;

    public Review(String description) {
        this.description = description;
    }

    public Review(Rating rating, String description) {
        this.rating = rating;
        this.description = description;
    }

}
