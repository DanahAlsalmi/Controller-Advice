package com.example.capstone2.Controller;

import com.example.capstone2.Model.Payroll;
import com.example.capstone2.Service.PayrollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollService payrollService;

    //Done test - get
    @GetMapping("/get")
    public ResponseEntity getAllPayroll(){
        return ResponseEntity.status(200).body(payrollService.getAllPayrolls());
    }

    //Done test - Add Payroll
    @PostMapping("/add")
    public ResponseEntity addPayroll(@Valid @RequestBody Payroll payroll){
        payrollService.addPayroll(payroll);
        return ResponseEntity.status(200).body("Payroll added successfully");
    }

    //Done test - Update Payroll
    @PutMapping("/update/{payrollId}")
    public ResponseEntity updateUser(@PathVariable Integer payrollId, @Valid @RequestBody Payroll payroll){
        payrollService.updatePayroll(payrollId, payroll);
        return ResponseEntity.status(200).body("Payroll updated successfully");
    }

    //Done test - Delete Payroll
    @DeleteMapping("/delete/{payrollId}")
    public ResponseEntity deleteUser(@PathVariable Integer payrollId){
        payrollService.deletePayroll(payrollId);
        return ResponseEntity.status(200).body("Payroll deleted successfully");
    }

    @PostMapping("/calculate-bonus/{employeeId}")
    public ResponseEntity calculateBonus(@PathVariable int employeeId) {
        String response = payrollService.calculateBonus(employeeId);
        return ResponseEntity.status(200).body(response);
    }


}
