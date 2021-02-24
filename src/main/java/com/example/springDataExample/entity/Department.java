package com.example.springDataExample.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Department {
    @Id
    @GenericGenerator(name="department_id_seq", strategy = "increment")
    @GeneratedValue(generator = "department_id_seq",strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @JoinColumn(referencedColumnName = "id",name = "department_id")
    @OneToMany
    List<Employee> employeeList;

}
