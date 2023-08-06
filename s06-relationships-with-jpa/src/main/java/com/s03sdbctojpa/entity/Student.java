package com.s03sdbctojpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    //for Shared Primary Key
/*    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn*/
    private Passport passport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "st_workstation",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "workstation_id", referencedColumnName = "id"))
    private Workstation workstation;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
