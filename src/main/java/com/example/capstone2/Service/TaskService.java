package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Employee;
import com.example.capstone2.Model.Task;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.EmployeeRepository;
import com.example.capstone2.Repository.TaskRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;


    //Get Task
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    //Update Task
    public void updateTask(Integer id ,Task task) {
        Task t = taskRepository.findTaskById(id);
        if(t == null) {
            throw new ApiException("Task not found");
        }
        t.setEmployeeId(task.getEmployeeId());
        t.setSupervisorId(task.getSupervisorId());
        t.setDeadline(task.getDeadline());
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        taskRepository.save(t);
    }

    // Assign a new task to an employee
    public Task assignTask(Task task) {
        User supervisor = userRepository.findUserByUserId(task.getSupervisorId());
        if (supervisor == null) {
            throw new ApiException("SUPERVISOR not found");
        }

        if (!supervisor.getRole().equalsIgnoreCase("SUPERVISOR")) {
            throw new ApiException("User is not authorized to assign tasks. Only supervisors can assign tasks.");
        }

        Employee employee = employeeRepository.findEmployeeByEmployeeId(task.getEmployeeId());
        if (employee == null) {
            throw new ApiException("Employee not found");
        }

        task.setEmployeeId(task.getEmployeeId());
        task.setSupervisorId(task.getSupervisorId());
        task.setStatus("NOT_COMPLETE");

        return taskRepository.save(task);
    }

    // Get all tasks for a specific employee
    public List<Task> getTasksForEmployee(int employeeId) {
        return taskRepository.findTaskByEmployeeId(employeeId);
    }

    // Mark a task as complete
    public Task markTaskAsComplete(Integer taskId) {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            throw new ApiException("Task not found");
        }

        task.setStatus("COMPLETE");
        return taskRepository.save(task);
    }

    // Get tasks assigned by a specific supervisor
    public List<String> getTasksBySupervisor(int supervisorId) {
        List<Task> tasks = taskRepository.findTaskBySupervisorId(supervisorId);
        List<String> taskDetails = new ArrayList<>();
        LocalDate now = LocalDate.now();


        for (Task task : tasks) {
            Employee employee = employeeRepository.findEmployeeByEmployeeId(task.getEmployeeId());
            if (employee != null) {
                String status = task.getDeadline().isBefore(now) ? " (Overdue)" : " (On Track)";
                String detail = "Task: " + task.getTitle() + ", Assigned to: " + employee.getFirstName() + " " + employee.getLastName()+" "+ ", Deadline: " + task.getDeadline() + status+" "+task.getStatus();
                taskDetails.add(detail);
            }
        }
        return taskDetails;
    }

}
