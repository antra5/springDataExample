package com.example.springDataExample.entity;

import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.lang.annotation.Documented;

@Document
@Getter
@Setter
public class Employee {
    @MongoId
    private Long id;
    private String name;
    private String departmentName;


}
