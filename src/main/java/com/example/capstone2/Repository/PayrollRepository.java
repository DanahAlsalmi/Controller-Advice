package com.example.capstone2.Repository;

import com.example.capstone2.Model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Integer> {

    @Query("select p from Payroll p where p.payrollId=?1")
    Payroll findPayrollByPayrollId(Integer payrollId);


    Payroll findPayrollByEmployeeIdAndPaymentDate(int employeeId, LocalDate paymentDate);

}
