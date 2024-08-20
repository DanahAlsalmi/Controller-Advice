package com.example.capstone2.Repository;

import com.example.capstone2.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select t from Task t where t.id=?1")
    Task findTaskById(Integer id);

    List<Task> findTaskByEmployeeId(int employeeId);
    List<Task> findTaskBySupervisorId(int supervisorId);
}
