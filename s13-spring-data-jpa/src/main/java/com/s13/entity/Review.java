package com.s13.entity;

import com.s13.Rating;
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
