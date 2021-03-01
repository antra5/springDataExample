package com.example.springDataExample.repository;

import com.example.springDataExample.entity.Department;
import com.example.springDataExample.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department,Long> {
    @Query(value = "select * from Employee e where e.years_of_experience=(select MAX(years_of_experience) from Employee e and e.department_id=?1)",nativeQuery = true)
    List<Employee> findMostExperiencedEmployees(Long departmentId);


}
