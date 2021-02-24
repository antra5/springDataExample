package com.example.springDataExample.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Data
public class Department {
    @Id
    @GenericGenerator(name="department_id_seq", strategy = "increment")
    @GeneratedValue(generator = "department_id_seq",strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    @JoinColumn
//    @OneToMany

}
