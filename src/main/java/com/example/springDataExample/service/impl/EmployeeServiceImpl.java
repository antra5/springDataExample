package com.example.springDataExample.service.impl;

import com.example.springDataExample.DTO.EmployeeRequestDTO;
import com.example.springDataExample.DTO.EmployeeResponseDTO;
import com.example.springDataExample.entity.Employee;
import com.example.springDataExample.repository.EmployeeRepository;
import com.example.springDataExample.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee=new Employee();

        //
        BeanUtils.copyProperties(employeeRequestDTO,employee);


        Employee savedEmployee=employeeRepository.save(employee);


        EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
        BeanUtils.copyProperties(savedEmployee,responseDTO);
        return responseDTO;
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            //copying from employee to response dto
            EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(),responseDTO);
            return responseDTO;
        }
        return null;
    }

    @Override
    public EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent())
        {
            Employee employeeFromDB=employeeOptional.get();
            //update DB
            employeeFromDB.setName(employeeRequestDTO.getName());
            employeeFromDB.setDepartmentName(employeeRequestDTO.getDepartmentName());
            //SAVE TO DB
            Employee savedEmployee=employeeRepository.save(employeeFromDB);
            EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
            //COPY FROM EMPLOYEE TO  RESPONSE DTO
            BeanUtils.copyProperties(savedEmployee,responseDTO);
            return responseDTO;
        }
        return null;
    }

    @Override
    public EmployeeResponseDTO deleteEmployeeById(Long id) {
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent())
        {
            //Employee employeeFromDB=employeeOptional.get();
            employeeRepository.deleteById(id);
            EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(),responseDTO);
            return responseDTO;

        }
        return null;
    }
}
