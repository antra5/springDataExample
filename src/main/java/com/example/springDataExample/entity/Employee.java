package com.example.springDataExample.entity;

import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.redis.core.RedisHash;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.lang.annotation.Documented;

@RedisHash
@Getter
@Setter
public class Employee {
   @Id
    private Long id;
    private String name;
    private String departmentName;


}
