package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Employee;
import com.example.capstone2.Model.Report;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.EmployeeRepository;
import com.example.capstone2.Repository.ReportRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    private final UserRepository userRepository;

    private final EmployeeRepository employeeRepository;

    //Get
    public List<Report> getAllReports(){
        return reportRepository.findAll();
    }

    // Employee submits a report
    public String submitReport(int employeeId, Report report) {
        Employee employee = employeeRepository.findEmployeeByEmployeeId(employeeId);

        if (employee == null) {
            throw new ApiException("Employee not found");
        }

        report.setStatus("PENDING");

        reportRepository.save(report);

        return "Report submitted and is pending approval.";
    }

    // supervisor approves or rejects a report
    public String approveOrRejectReport(Integer reportId, String status, int userId) {
        Report report = reportRepository.findReportById(reportId);
        User user = userRepository.findUserByUserId(userId);

        if (report == null) {
            throw new ApiException("Report not found");
        }

        if (user == null || (!user.getRole().equals("SUPERVISOR"))) {
            throw new ApiException("User is not authorized to approve or reject this report.");
        }

        if (!status.equals("APPROVED") && !status.equals("REJECTED")) {
            throw new ApiException("Invalid status value. Must be 'APPROVED' or 'REJECTED'.");
        }

        report.setStatus(status);
        reportRepository.save(report);

        return status.equals("APPROVED") ? "Report approved." : "Report rejected.";
    }

    // Employee checks the status of their report
    public String checkReportStatus(Integer reportId) {
        Report report = reportRepository.findReportById(reportId);
        if (report == null) {
            throw new ApiException("Report not found");
        }
        return report.getStatus();
    }

    // Get reports for Employee
    public List<Report> getReportsForEmployee(int employeeId) {
        return reportRepository.findAllByEmployeeId(employeeId);
    }

    // Get reports for Supervisor
    public List<Report> getAllReportsForSupervisor(int userId) {
        User user = userRepository.findUserByUserId(userId);

        if (user == null || (!user.getRole().equals("SUPERVISOR"))) {
            throw new ApiException("User is not authorized to view reports.");
        }
        return reportRepository.findAll();
    }

}
