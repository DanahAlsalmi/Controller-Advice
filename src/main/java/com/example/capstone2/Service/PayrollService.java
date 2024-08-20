package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Employee;
import com.example.capstone2.Model.Payroll;
import com.example.capstone2.Repository.EmployeeRepository;
import com.example.capstone2.Repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;

    //Get
    public List<Payroll> getAllPayrolls(){
        return payrollRepository.findAll();
    }

    //Add Payroll
    public Payroll addPayroll(Payroll payroll) {
        Employee e = employeeRepository.findEmployeeByEmployeeId(payroll.getEmployeeId());
        if(e == null){
            throw new ApiException("Employee not found");
        }
        return payrollRepository.save(payroll);
    }

    //Update Payroll
    public void updatePayroll(Integer id ,Payroll payroll) {
        Payroll p = payrollRepository.findPayrollByPayrollId(id);
        if(p == null) {
            throw new ApiException("Payroll not found");
        }
        p.setEmployeeId(payroll.getEmployeeId());
        p.setBasicSalary(payroll.getBasicSalary());
        p.setBonuses(payroll.getBonuses());
        p.setTotalPay(payroll.getTotalPay());
        payrollRepository.save(p);
    }

    //Delete Payroll
    public void deletePayroll(Integer id) {
        Payroll p = payrollRepository.findPayrollByPayrollId(id);
        if(p == null) {
            throw new ApiException("Payroll not found");
        }
        payrollRepository.delete(p);
    }

    //Bonus
    public String calculateBonus(int employeeId) {
        Employee employee = employeeRepository.findEmployeeByEmployeeId(employeeId);

        if (employee == null) {
            return "Employee does not exist.";
        }

        // Calculate years of service
        LocalDate hireDate = employee.getDateOfJoining();
        long yearsOfService = ChronoUnit.YEARS.between(hireDate, LocalDate.now());

        if (yearsOfService < 1) {
            return "Employee has not completed a year yet. No bonus applicable.";
        }

        LocalDate paymentDate = LocalDate.now().withDayOfMonth(1);
        Payroll payroll = payrollRepository.findPayrollByEmployeeIdAndPaymentDate(employeeId, paymentDate);
        if (payroll == null) {
            payroll = new Payroll();
            payroll.setEmployeeId(employeeId);
            payroll.setPaymentDate(paymentDate);
            payroll.setBasicSalary(employee.getSalary());
        }

        payroll.setBonuses(payroll.getBasicSalary()*yearsOfService);
        payroll.setTotalPay(payroll.getBasicSalary() + payroll.getBonuses());
        payrollRepository.save(payroll);

        return "Bonus of " + payroll.getBonuses() + " added to the payroll for employee " + employeeId;
    }


}
