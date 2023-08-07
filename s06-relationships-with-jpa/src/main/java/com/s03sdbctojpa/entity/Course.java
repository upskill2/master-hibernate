package com.s03sdbctojpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    private String name;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)")
    private LocalDateTime createdDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private List<Review> reviews = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    protected Course() {
    }

    public Course(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setCourse(this);
    }

    public void removeReviews(Review review) {
        this.reviews.remove(review);
    }

}
