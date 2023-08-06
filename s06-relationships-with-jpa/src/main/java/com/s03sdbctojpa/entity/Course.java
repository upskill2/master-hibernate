package com.s03sdbctojpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
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

    public Course(String name) {
        this.name = name;
    }

    protected Course() {
    }

    public Course(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
