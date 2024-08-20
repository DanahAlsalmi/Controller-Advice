package com.example.capstone2.Repository;

import com.example.capstone2.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query("select r from Report r where r.reportId=?1")
    Report findReportById(Integer id);

    List<Report> findAllByEmployeeId(int employeeId);
}
