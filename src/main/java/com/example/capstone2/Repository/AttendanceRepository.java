package com.example.capstone2.Repository;

import com.example.capstone2.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    @Query("select a from Attendance a where a.attendanceId=?1")
    Attendance findAttendanceByAttendanceId(Integer attendanceId);

    Attendance findAttendanceByEmployeeIdAndClockInTimeBetween(int employeeId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
