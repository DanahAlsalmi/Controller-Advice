package com.example.capstone2.Controller;

import com.example.capstone2.Model.Department;
import com.example.capstone2.Service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/get")
    public ResponseEntity getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/add")
    public ResponseEntity createDepartment(@Valid @RequestBody Department department) {
         departmentService.createDepartment(department);
        return ResponseEntity.status(201).body("Department created successfully");
    }



    @GetMapping("/by-department/{departmentName}")
    public ResponseEntity getEmployeesByDepartment(@PathVariable String departmentName) {
        List<Map<String, String>> employees = departmentService.getEmployeesByDepartmentName(departmentName);
        return ResponseEntity.ok(employees);
    }
}
