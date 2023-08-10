package com.s13.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "student")
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@org.hibernate.annotations.Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
@SQLDelete (sql = "UPDATE student SET is_deleted = true WHERE id = ?")
@Where (clause = "is_deleted = false")
public class Student {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "email")
    private String email;

    @Embedded
    private Address address;

    @Column (name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;

    @JsonIgnore
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "passport_id", referencedColumnName = "id")
    //for Shared Primary Key
/*    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn*/
    private Passport passport;

    @JsonIgnore
    @OneToOne (cascade = CascadeType.ALL)
    @JoinTable (name = "st_workstation",
            joinColumns = @JoinColumn (name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (name = "workstation_id", referencedColumnName = "id"))
    private Workstation workstation;

    @JsonIgnore
    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable (name = "student_course",
            joinColumns = @JoinColumn (name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses = new HashSet<> ();

    public void addCourse (Course course) {
        this.courses.add (course);
    }

    public void removeCourse (Course course) {
        this.courses.remove (course);
    }

    public Student (String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
