package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;


    @NotNull(message = "Employee Id should not be null")
    @Column(columnDefinition = "int not null")
    private int employeeId;

    @Pattern(regexp = "VACATION|RESIGNATION|SICK_LEAVE",message = "role must be VACATION|RESIGNATION|SICK_LEAVE")
    @Column(columnDefinition = "varchar(20) not null")
    @Check(constraints = "reportType IN ('VACATION','RESIGNATION','SICK_LEAVE')")
    private String reportType;

    @Pattern(regexp = "PENDING|APPROVED|REJECTED",message = "role must be PENDING|APPROVED|REJECTED")
    @Column(columnDefinition = "varchar(20) not null")
    @Check(constraints = "status IN ('PENDING','APPROVED','REJECTED')")
    private String status;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String reason;
}
