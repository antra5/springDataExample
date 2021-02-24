package com.example.springDataExample.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GenericGenerator(name="employee_id_seq", strategy = "increment")
    @GeneratedValue(generator = "employee_id_seq",strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private Department department;


}
