package com.example.springDataExample.repository;

import com.example.springDataExample.entity.Department;
import com.example.springDataExample.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department,Long> {
//    @Query(value = "select * from Employee e where e.years_of_experience=(select MAX(years_of_experience) from Employee e and e.department_id=?1)",nativeQuery = true)
//    List<Employee> findMostExperiencedEmployees(Long departmentId);
    @Query(value="select * from department where id in(select id from department where (select max(sum) from (select sum(years_of_experience) from employee e1 group by e1.department_id) s1) = (select sum(years_of_experience) from employee e2 where e2.department_id = department.id))",nativeQuery = true)
    List<Department> findDepartmentWithMaxExperience();


}
