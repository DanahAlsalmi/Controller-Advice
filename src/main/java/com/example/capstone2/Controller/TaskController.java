package com.example.capstone2.Controller;


import com.example.capstone2.Model.Task;
import com.example.capstone2.Service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/assign")
    public ResponseEntity assignTask(@Valid @RequestBody Task task) {
        Task assignedTask = taskService.assignTask(task);
        return ResponseEntity.status(201).body("Successfully assigned task to employee ");
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity getTasksForEmployee(@PathVariable int employeeId) {
        List<Task> tasks = taskService.getTasksForEmployee(employeeId);
        return ResponseEntity.status(200).body(tasks);
    }

    @PutMapping("/complete/{taskId}")
    public ResponseEntity markTaskAsComplete(@PathVariable Integer taskId) {
        Task completedTask = taskService.markTaskAsComplete(taskId);
        return ResponseEntity.status(200).body("Successfully marked task as complete: " + completedTask);
    }

    @GetMapping("/supervisor-task/{supervisorId}")
    public ResponseEntity getTasksBySupervisor(@PathVariable int supervisorId) {
        List<String> tasks = taskService.getTasksBySupervisor(supervisorId);
        return ResponseEntity.status(200).body(tasks);
    }

}
