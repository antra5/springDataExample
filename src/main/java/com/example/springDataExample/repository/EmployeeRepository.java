package com.example.springDataExample.repository;

import com.example.springDataExample.entity.Department;
import com.example.springDataExample.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Long>
{
    List<Employee> findByDepartment(Department department);
    List<Employee> findByDepartment_Id(Long departmentId);
    //@Query(value = "SELECT * from Employee e where e.department_id")=?1)
    //@Query("FROM Employee e WHERE e.department.id=71")
    @Query(value = "SELECT e FROM Employee e WHERE e.department.id=?1", nativeQuery = true)
    List<Employee> getEmployeeListByNativeQuery(Long departmentId);
    //List<Employee> getEmployeeListByDepartmentId(Long departmentId);
    //OR List<Employee> getEmployeeListByDepartmentId( @Param("departmentId") Long departmentId)
}
