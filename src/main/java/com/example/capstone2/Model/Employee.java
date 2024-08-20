package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @NotNull(message = "User ID is required")
    @Column(columnDefinition = "int not null")
    private int userId;

    @NotEmpty(message = "First name is required")
    @Column(columnDefinition = "varchar(10) not null")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Column(columnDefinition = "varchar(10) not null")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @Column(columnDefinition = "date not null")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Gender is required")
    @Pattern(regexp = "MALE|FEMALE", message = "Gender must be MALE, FEMALE")
    @Column(columnDefinition = "varchar(6) not null")
    @Check(constraints = "gender IN ('MALE','FEMALE')")
    private String gender;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^(\\+9665|05|5)([0-9]{8})$", message = "Invalid phone number format")
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @NotEmpty(message = "Position is required")
    @Size(min = 2, max = 100, message = "Position must be between 2 and 100 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String position;

    @NotNull(message = "Date of joining is required")
    @PastOrPresent(message = "Date of joining must be in the past or present")
    @Column(columnDefinition = "date not null")
    private LocalDate dateOfJoining;

    @NotNull(message = "Salary is required")
    @Positive
    @Column(columnDefinition = "decimal(10,2) not null")
    private Double salary;

    @NotEmpty(message = "Status is required")
    @Pattern(regexp = "ACTIVE|ON LEAVE", message = "Status must be 'ACTIVE' OR 'ON LEAVE'")
    @Column(columnDefinition = "varchar(8)")
    @Check(constraints = "status IN ('ACTIVE','ON LEAVE')")
    private String status;

    @NotEmpty(message = "Department is required")
    @Column(columnDefinition = "varchar(100) not null")
    private String departmentName;


}
