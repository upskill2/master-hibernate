package com.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student_workstation")
@Getter
@Setter
@NoArgsConstructor
public class Workstation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pc_name")
    private String pCname;

    @Column(name = "pc_type")
    private String pCType;

    @Column(name = "os")
    private String os;

    @OneToOne(mappedBy = "workstation")
    private Student student;

    public Workstation(String pCname, String pCType, String os) {
        this.pCname = pCname;
        this.pCType = pCType;
        this.os = os;
    }

}
