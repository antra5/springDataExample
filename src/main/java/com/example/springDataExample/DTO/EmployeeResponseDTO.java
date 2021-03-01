package com.example.springDataExample.DTO;

import com.example.springDataExample.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String code;
    private int yearsOfExperience;
    private DepartmentResponseDTO department;
    public void setDepartmentFromEntity(Department departmentEntity)
    {
        DepartmentResponseDTO departmentResponseDTO=new DepartmentResponseDTO();
        departmentResponseDTO.setId(departmentEntity.getId());
        departmentResponseDTO.setName(departmentEntity.getName());
        this.department=departmentResponseDTO;

    }
}
