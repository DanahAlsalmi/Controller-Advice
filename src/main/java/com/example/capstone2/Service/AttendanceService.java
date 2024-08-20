package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Attendance;
import com.example.capstone2.Model.Employee;
import com.example.capstone2.Repository.AttendanceRepository;
import com.example.capstone2.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    private static final double REGULAR_HOURS = 8.0;
    private static final double OVERTIME_MULTIPLIER = 1.5;
    private static final int DAYS_IN_MONTH = 30;


    //Get All
    public List<Attendance> getAllAttendance(){
        return attendanceRepository.findAll();
    }

    //Add
    public Attendance addAttendance(Attendance attendance){
        attendance.setDate(LocalDate.now());
        attendance.setClockInTime(LocalDateTime.now());
        return attendanceRepository.save(attendance);
    }

    //Update
    public void updateAttendance(Integer id , Attendance attendance){
        Attendance a = attendanceRepository.findAttendanceByAttendanceId(id);
        if(a == null){
            throw new ApiException("Attendance not found");
        }
        a.setEmployeeId(attendance.getEmployeeId());
        a.setDate(attendance.getDate());
        a.setClockInTime(attendance.getClockInTime());
        a.setClockOutTime(attendance.getClockOutTime());
        a.setTotalHours(attendance.getTotalHours());
        a.setStatus(attendance.getStatus());
        attendanceRepository.save(a);
    }

    //Delete
    public void deleteAttendance(Integer id){
        Attendance a = attendanceRepository.findAttendanceByAttendanceId(id);
        if(a == null){
            throw new ApiException("Attendance not found");
        }
        attendanceRepository.delete(a);
    }

    public Attendance findAttendanceByEmployeeId(int employeeId) {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
        return attendanceRepository.findAttendanceByEmployeeIdAndClockInTimeBetween(employeeId, startOfDay, endOfDay);
    }

    // Handle clock-in logic
    public String clockIn(int employeeId) {
        Employee employee = employeeRepository.findEmployeeByEmployeeId(employeeId);

        if (employee == null) {
            return "Employee does not exist.";
        }
        Attendance attendance = findAttendanceByEmployeeId(employeeId);
        if (attendance != null && attendance.getClockOutTime() == null) {
            return "Already clocked in.";
        }

        Attendance newAttendance = new Attendance();
        newAttendance.setDate(LocalDate.now());
        newAttendance.setEmployeeId(employeeId);
        newAttendance.setClockInTime(LocalDateTime.now());
        newAttendance.setStatus("Clocked In");

        attendanceRepository.save(newAttendance);
        return "Clocked in successfully.";
    }

    // Handle clock-out logic
    public String clockOut(int employeeId) {
        Employee employee = employeeRepository.findEmployeeByEmployeeId(employeeId);

        if (employee == null) {
            return "Employee does not exist.";
        }
        Attendance attendance = findAttendanceByEmployeeId(employeeId);
        if (attendance == null) {
            return "No active clock-in found.";
        }
        if (attendance.getClockOutTime() != null) {
            return "Already clocked out.";
        }

        attendance.setClockOutTime(LocalDateTime.now());
        calculateHoursWorkedAndOvertime(attendance);
        attendance.setStatus("Clocked Out");  // Update the status

        attendanceRepository.save(attendance);

        return "Clocked out successfully. Hours worked: " + attendance.getTotalHours();
    }

    // Calculate hours worked
    public void calculateHoursWorkedAndOvertime(Attendance attendance) {
        if (attendance.getClockOutTime() != null) {
            Duration duration = Duration.between(attendance.getClockInTime(), attendance.getClockOutTime());
            double hoursWorked = duration.toHours() + (duration.toMinutesPart() / 60.0);
            attendance.setTotalHours(hoursWorked);

            // Calculate overtime if hours worked exceed regular hours
            double overtime = hoursWorked > REGULAR_HOURS ? hoursWorked - REGULAR_HOURS : 0.0;
            attendance.setOvertimeHours(overtime);
        }
    }

    public double calculateOvertimePay(Integer attendanceId) {
        Attendance attendance = attendanceRepository.findAttendanceByAttendanceId(attendanceId);
        if (attendance == null) {
            throw new ApiException("Attendance not found");
        }

        Employee employee = employeeRepository.findEmployeeByEmployeeId(attendance.getEmployeeId());
        if (employee == null) {
            throw new ApiException("Employee not found");
        }

        double dailyRate = employee.getSalary() / DAYS_IN_MONTH;
        double hourlyRate = dailyRate / REGULAR_HOURS;

        double OvertimeHours = attendance.getOvertimeHours() * OVERTIME_MULTIPLIER;

        // Calculate the total overtime pay
        double overtimePay = OvertimeHours * hourlyRate;

        return overtimePay;
    }
}
