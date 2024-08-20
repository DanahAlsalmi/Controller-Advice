package com.example.capstone2.Controller;

import com.example.capstone2.Model.Employee;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    //Done test - Get
    @GetMapping("/get")
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    //Done test - Add
    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.status(201).body("Employee added successfully");
    }

    //Update
    @PutMapping("/update/{empId}")
    public ResponseEntity updateEmployee(@PathVariable Integer empId,@Valid @RequestBody Employee employee) {
        employeeService.updateEmployee(empId,employee);
        return ResponseEntity.status(201).body("Employee updated successfully");
    }

    //Delete
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity deleteEmployee(@PathVariable Integer empId) {
        employeeService.deleteEmployee(empId);
        return ResponseEntity.status(201).body("Employee deleted successfully");
    }


    @GetMapping("/by-role/{role}")
    public ResponseEntity getEmployeesByRole(@PathVariable String role) {
        List<User> employees = employeeService.getEmployeesByRole(role);
        return ResponseEntity.ok(employees);
    }

}
