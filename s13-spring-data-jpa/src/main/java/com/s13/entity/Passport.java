package com.s13.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "passport")
@Getter
@Setter
@NoArgsConstructor
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @Column(name = "passport_id")
    private long id;

    @Column(name = "passport_number", nullable = false)
    private String number;

      @OneToOne (mappedBy = "passport")
      //for Shared Primary Key
/*    @OneToOne
    @MapsId
    @JoinColumn(name = "passport_id")*/
    private Student student;

    public Passport(String number) {
        this.number = number;
    }

    public Passport(String number, Student student) {
        this.number = number;
        this.student = student;
    }

}
