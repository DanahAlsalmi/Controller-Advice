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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username is required")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, message = "Password must be more than 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Password must contain letter and number")

    @Column(columnDefinition = "varchar (15) not null")
    @Check(constraints = "LENGTH(password) >= 8")
    @Check(constraints = "password ~ '^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$'")
    private String password;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")

    @Column(columnDefinition = "varchar(40) not null unique ")
    @Check(constraints = "email LIKE '%@%'")
    private String email;

    @NotEmpty(message = "Role is required")
    @Pattern(regexp = "HR MANAGER|EMPLOYEE|SUPERVISOR", message = "role must be HR MANAGER|EMPLOYEE|SUPERVISOR")
    @Column(columnDefinition = "varchar(12) not null")
    @Check(constraints = "role IN ('HR MANAGER','EMPLOYEE','SUPERVISOR')")
    private String role;

    private LocalDate createdAt;


}
