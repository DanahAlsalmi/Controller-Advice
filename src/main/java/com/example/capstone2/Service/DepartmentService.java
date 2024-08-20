package com.example.capstone2.Service;

import com.example.capstone2.Model.Department;
import com.example.capstone2.Model.Employee;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.DepartmentRepository;
import com.example.capstone2.Repository.EmployeeRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;


    //Create
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    //Get
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    //Get employees by department name
    public List getEmployeesByDepartmentName(String departmentName) {
        List<Employee> employees = employeeRepository.findEmployeeByDepartmentName(departmentName);
        List<String> result = new ArrayList<>();

        for (Employee employee : employees) {
            String name = employee.getFirstName() + " " + employee.getLastName();

            User user = userRepository.findUserByUserId(employee.getUserId());
            String role = (user != null) ? user.getRole() : "No role assigned";

            String department = employee.getDepartmentName();

            String employeeInfo = "Name: " + name + ", Role: " + role + ", Department: " + department;
            result.add(employeeInfo);
        }

        return result;
    }
}
