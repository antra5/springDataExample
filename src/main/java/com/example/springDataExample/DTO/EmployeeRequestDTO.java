package com.example.springDataExample.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter

public class EmployeeRequestDTO {
    private Long id;
    private String name;
    private String departmentName;
}
