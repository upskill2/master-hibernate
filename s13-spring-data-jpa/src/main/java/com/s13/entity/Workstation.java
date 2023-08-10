package com.s13.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;

@Entity
@Table(name = "student_workstation")
@Getter
@Setter
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    @JsonIgnore
    @OneToOne(mappedBy = "workstation")
    private Student student;

    public Workstation(String pCname, String pCType, String os) {
        this.pCname = pCname;
        this.pCType = pCType;
        this.os = os;
    }

}
