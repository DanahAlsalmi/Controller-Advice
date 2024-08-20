package com.example.capstone2.Controller;

import com.example.capstone2.Model.Report;
import com.example.capstone2.Service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/get")
    public ResponseEntity getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }


    @PostMapping("/submit/{employeeId}")
    public ResponseEntity submitReport(@PathVariable int employeeId, @Valid @RequestBody Report report) {
        String response = reportService.submitReport(employeeId,report);
        return ResponseEntity.status(200).body(response);
    }


    @PostMapping("/approve/{reportId}/{userId}/{status}")
    public ResponseEntity approveReport(@PathVariable Integer reportId, @PathVariable int userId, @PathVariable String status) {

        String response = reportService.approveOrRejectReport(reportId, status, userId);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/status/{reportId}")
    public ResponseEntity checkReportStatus(@PathVariable Integer reportId) {
        String status = reportService.checkReportStatus(reportId);
        return ResponseEntity.status(200).body(status);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity getReportsForEmployee(@PathVariable int employeeId) {
        List<Report> reports = reportService.getReportsForEmployee(employeeId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/supervisor/{userId}")
    public ResponseEntity getAllReportsForSupervisor(@PathVariable int userId) {
        List<Report> reports = reportService.getAllReportsForSupervisor(userId);
        return ResponseEntity.status(200).body(reports);
    }
}
