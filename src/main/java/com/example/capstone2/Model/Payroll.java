package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payrollId;

    @NotNull(message = "Employee is required")
    @Column(columnDefinition = "int not null")
    private int employeeId;

    @NotNull(message = "Basic salary is required")
    @Column(columnDefinition = "decimal(10,2) not null")
    private Double basicSalary;

    @Column(columnDefinition = "decimal(10,2) DEFAULT 0.0")
    private Double bonuses = 0.0;
    private LocalDate paymentDate;

    @NotNull(message = "Total pay is required")
    @Column(columnDefinition = "decimal(10,2) not null")
    private Double totalPay;
}
