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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Employee Id should not be null")
    @Column(columnDefinition = "int not null")
    private int employeeId;

    @NotNull(message = "Supervisor Id should not be null")
    @Column(columnDefinition = "int not null")
    private int supervisorId;

    @NotEmpty(message = "title must be not Empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;

    @NotEmpty(message = "Description must be not Empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String description;

    private LocalDate deadline;

    @Pattern(regexp = "COMPLETE|NOT_COMPLETE", message = "Status must be COMPLETE or NOT_COMPLETE")
    @Column(columnDefinition = "varchar(20) default 'NOT_COMPLETE'")
    @Check(constraints = "status IN ('COMPLETE','NOT_COMPLETE')")
    private String status = "NOT_COMPLETE";
}
