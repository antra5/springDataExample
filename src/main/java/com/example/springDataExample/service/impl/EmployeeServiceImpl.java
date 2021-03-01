package com.example.springDataExample.service.impl;

import com.example.springDataExample.DTO.DepartmentResponseDTO;
import com.example.springDataExample.DTO.EmployeeRequestDTO;
import com.example.springDataExample.DTO.EmployeeResponseDTO;
import com.example.springDataExample.entity.Department;
import com.example.springDataExample.entity.Employee;
import com.example.springDataExample.repository.DepartmentRepository;
import com.example.springDataExample.repository.EmployeeRepository;
import com.example.springDataExample.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee=new Employee();

        Optional<Department> departmentOptional=departmentRepository.findById(employeeRequestDTO.getDepartment().getId());
        BeanUtils.copyProperties(employeeRequestDTO,employee);

        if(departmentOptional.isPresent()) {
            employee.setDepartment(departmentOptional.get());
        }
        else
        {
            Department department=new Department();
            department.setName(employeeRequestDTO.getDepartment().getName());
            employee.setDepartment(department);

        }


        Employee savedEmployee=employeeRepository.save(employee);


        EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
        BeanUtils.copyProperties(savedEmployee,responseDTO);

       // DepartmentResponseDTO departmentResponseDTO=new DepartmentResponseDTO();
        //departmentResponseDTO.setId(savedEmployee.getDepartment().getId());
        ///departmentResponseDTO.setName(savedEmployee.getDepartment().getName());

        responseDTO.setDepartmentFromEntity(employee.getDepartment());
        return responseDTO;
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            //copying from employee to response dto
            EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(),responseDTO);
            responseDTO.setDepartmentFromEntity(employeeOptional.get().getDepartment());
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
            Optional<Department> departmentOptional=departmentRepository.findById(employeeRequestDTO.getId());
            if(departmentOptional.isPresent()) {
                employeeFromDB.setDepartment(departmentOptional.get());
            }
            else
            {
                    Department department=new Department();
                    department.setName(employeeRequestDTO.getName());
                    employeeFromDB.setDepartment(department);

            }
//            SAVE TO DB
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

    @Override
    public List<EmployeeResponseDTO> getEmployeeListByDepartment(Long departmentId) {
        //Department department=departmentRepository.findById(departmentId).get();
        //List<Employee> employeeList=employeeRepository.findByDepartment(department);
        List<EmployeeResponseDTO> employeeResponseDTOList=new ArrayList<>();
        List<Employee> employeeList=employeeRepository.getEmployeeListByNativeQuery(departmentId);
        for(Employee employee:employeeList)
        {
            EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
            BeanUtils.copyProperties(employee,responseDTO);
            responseDTO.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDTOList.add(responseDTO);
        }
        return employeeResponseDTOList;
    }

    @Override
    public List<EmployeeResponseDTO> getMostExperiencedEmployee() {
        List<EmployeeResponseDTO> employeeResponseDTOList=new ArrayList<>();
        List<Employee> employeeList=employeeRepository.findMostExperiencedEmployees();
        for(Employee employee:employeeList)
        {
            EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
            BeanUtils.copyProperties(employee,responseDTO);
            responseDTO.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDTOList.add(responseDTO);
        }
        return employeeResponseDTOList;

    }
}
