package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Employee;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.EmployeeRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final UserRepository userRepository;

    //Get All Employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //Add Employee
    public Employee addEmployee(Employee employee) {
        User user = userRepository.findUserByUserId(employee.getUserId());
        if(user == null) {
            throw new ApiException("User not found");
        }
        return employeeRepository.save(employee);
    }

    //Update Employee
    public Employee updateEmployee(Integer employeeId ,Employee employee) {
        Employee emp = employeeRepository.findEmployeeByEmployeeId(employeeId);
        if(emp == null) {
            throw new ApiException("Employee not found");
        }
        emp.setUserId(employee.getUserId());
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setDateOfBirth(employee.getDateOfBirth());
        emp.setGender(employee.getGender());
        emp.setPhoneNumber(employee.getPhoneNumber());
        emp.setPosition(employee.getPosition());
        emp.setDateOfJoining(employee.getDateOfJoining());
        emp.setSalary(employee.getSalary());
        emp.setStatus(employee.getStatus());
        return employeeRepository.save(emp);
    }

    //Delete Employee
    public void deleteEmployee(Integer employeeId) {
        Employee emp = employeeRepository.findEmployeeByEmployeeId(employeeId);
        if(emp == null) {
            throw new ApiException("Employee not found");
        }
        employeeRepository.delete(emp);
    }

    public List<User> getEmployeesByRole(String role) {
        return userRepository.findUserByRole(role);
    }


}
