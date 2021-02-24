package com.example.springDataExample.controller;

import com.example.springDataExample.DTO.DepartmentRequestDTO;
import com.example.springDataExample.DTO.DepartmentResponseDTO;
import com.example.springDataExample.DTO.EmployeeRequestDTO;
import com.example.springDataExample.DTO.EmployeeResponseDTO;
import com.example.springDataExample.entity.Department;
import com.example.springDataExample.repository.DepartmentRepository;
import com.example.springDataExample.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseDTO createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO)
    {
        return departmentService.createDepartment(departmentRequestDTO);

    }
    @GetMapping(path = "/{id}")
    private Department getDepartment(@PathVariable("id") Long id)
    {
        return departmentService.getDepartmentById(id);
    }
    @PutMapping(path = "/{id}")
    private  DepartmentResponseDTO updateDepartment(@PathVariable("id") Long departmentId,
                                                    @RequestBody DepartmentRequestDTO departmentRequestDTO)
    {
        return departmentService.updateDepartment(departmentId,departmentRequestDTO);
    }



}
