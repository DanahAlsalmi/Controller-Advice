package com.example.capstone2.Repository;

import com.example.capstone2.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("select e from Employee e where e.employeeId=?1")
    Employee findEmployeeByEmployeeId(Integer employeeId);

    Employee findEmployeeByUserId(int userId);

    List<Employee> findEmployeeByDepartmentName(String departmentName);

}
