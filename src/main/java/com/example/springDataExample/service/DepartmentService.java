package com.example.springDataExample.service;

import com.example.springDataExample.DTO.DepartmentRequestDTO;
import com.example.springDataExample.DTO.DepartmentResponseDTO;
import com.example.springDataExample.DTO.EmployeeRequestDTO;
import com.example.springDataExample.DTO.EmployeeResponseDTO;
import com.example.springDataExample.entity.Department;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);
    //DepartmentResponseDTO updateDepartmentById(Long id, DepartmentRequestDTO departmentRequestDTO);
    Department getDepartmentById(Long id);

    DepartmentResponseDTO updateDepartment(Long departmentId, DepartmentRequestDTO departmentRequestDTO);
}
