package com.example.capstone2.Controller;

import com.example.capstone2.Model.Attendance;
import com.example.capstone2.Service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    //Get
    @GetMapping("/get")
    public ResponseEntity getAttendance() {
        return ResponseEntity.status(200).body(attendanceService.getAllAttendance());
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity addAttendance(@Valid @RequestBody Attendance attendance) {
        attendanceService.addAttendance(attendance);
        return ResponseEntity.status(201).body("Attendance added successfully");
    }

    //Update
    @PutMapping("/update/{attendanceId}")
    public ResponseEntity updateAttendance(@PathVariable Integer attendanceId, @Valid @RequestBody Attendance attendance) {
        attendanceService.updateAttendance(attendanceId, attendance);
        return ResponseEntity.status(201).body("Attendance updated successfully");
    }

    //Delete
    @DeleteMapping("/delete/{attendanceId}")
    public ResponseEntity deleteAttendance(@PathVariable Integer attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
        return ResponseEntity.status(201).body("Attendance deleted successfully");
    }


    @PostMapping("/clock-in/{employeeId}")
    public ResponseEntity clockIn(@PathVariable int employeeId) {
        String response = attendanceService.clockIn(employeeId);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/clock-out/{employeeId}")
    public ResponseEntity clockOut(@PathVariable int employeeId) {
        String response = attendanceService.clockOut(employeeId);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/overtime-pay/{attendanceId}")
    public ResponseEntity getOvertimePay(@PathVariable Integer attendanceId) {
        double overtimePay = attendanceService.calculateOvertimePay(attendanceId);
        return ResponseEntity.status(200).body(overtimePay);
    }
}
