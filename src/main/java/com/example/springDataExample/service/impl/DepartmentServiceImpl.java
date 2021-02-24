package com.example.springDataExample.service.impl;

import com.example.springDataExample.DTO.DepartmentRequestDTO;
import com.example.springDataExample.DTO.DepartmentResponseDTO;
import com.example.springDataExample.DTO.EmployeeResponseDTO;
import com.example.springDataExample.entity.Department;
import com.example.springDataExample.entity.Employee;
import com.example.springDataExample.repository.DepartmentRepository;
import com.example.springDataExample.repository.EmployeeRepository;
import com.example.springDataExample.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        Department department=new Department();
        BeanUtils.copyProperties(departmentRequestDTO,department);
        Department savedDepartment=departmentRepository.save(department);

        DepartmentResponseDTO responseDTO=new DepartmentResponseDTO();
        BeanUtils.copyProperties(savedDepartment,responseDTO);
        return responseDTO;
    }

//    @Override
//    public DepartmentResponseDTO updateDepartmentById(Long id, DepartmentRequestDTO departmentRequestDTO) {
//        Department department=new Department();
//        Optional<Department> optionalDepartment=departmentRepository.findById(id);
//        if (optionalDepartment.isPresent())
//        {
//            Department departmentFromDB= optionalDepartment.get();
//            departmentFromDB.setName(departmentRequestDTO.getName());
//            Department savedDepartment=departmentRepository.save(departmentFromDB);
//            DepartmentResponseDTO departmentResponseDTO=new DepartmentResponseDTO();
//            BeanUtils.copyProperties(savedDepartment,departmentResponseDTO);
//            return departmentResponseDTO;
//        }
//        return null;
//    }


    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public DepartmentResponseDTO updateDepartment(Long departmentId, DepartmentRequestDTO departmentRequestDTO) {
        Department department=departmentRepository.findById(departmentId).get();
        List<Employee> employeeList=employeeRepository.findByDepartment_Id(departmentId);
        //update department
        department.setName(departmentRequestDTO.getName());
        Department savedDepartment=departmentRepository.save(department);

//        if(departmentId!=null)
//        {
//            throw new RuntimeException("My error!");
//        }

        //append department code to employee code
        employeeList.forEach(employee -> {
            employee.setCode(departmentRequestDTO.getDepartmentCode());
        });
        employeeRepository.saveAll(employeeList);


        DepartmentResponseDTO responseDTO=new DepartmentResponseDTO();
        BeanUtils.copyProperties(savedDepartment,responseDTO);
        return responseDTO;

    }
}
