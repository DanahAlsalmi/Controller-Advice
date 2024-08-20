package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceId;

    @NotNull(message = "Employee is required")
    @Column(columnDefinition = "int not null")
    private int employeeId;

//    @NotNull(message = "Date is required")
    @Column(columnDefinition = "DATE")
    private LocalDate date;

//    @NotEmpty(message = "Clock-in time is required")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime clockInTime;

//    @NotEmpty(message = "Clock-out time is required")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime clockOutTime;

    @Column(columnDefinition = "DECIMAL(5,2) DEFAULT 0.0")
    private Double totalHours = 0.0;
    private Double overtimeHours;

    @NotEmpty(message = "Status is required")
    @Pattern(regexp = "PRESENT|ABSENT", message = "Status must be 'PRESENT',or 'ABSENT'")
    @Column(columnDefinition = "varchar(50) not null ")
    @Check(constraints = "status IN ('PRESENT','ABSENT')")
    private String status;

}
