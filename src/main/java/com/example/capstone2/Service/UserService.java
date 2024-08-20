package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Employee;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.EmployeeRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;


    //Get
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Add User
    public User addUser(User user) {
        user.setCreatedAt(LocalDate.now());
        return userRepository.save(user);
    }

    //Update User
    public void updateUser(Integer id ,User user) {
        User u = userRepository.findUserByUserId(id);
        if(u == null) {
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        userRepository.save(u);
    }

    //Delete User
    public void deleteUser(Integer id) {
        User u = userRepository.findUserByUserId(id);
        if(u == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }

    //Profile
    public Map<String, Object> getUserProfile(Integer userId) {
        User user = userRepository.findUserByUserId(userId);
        Employee employee = employeeRepository.findEmployeeByUserId(userId);

        if(user == null) {
            throw new ApiException("User not found");
        }
        if(employee == null) {
            throw new ApiException("Employee not found");
        }

        Map<String, Object> userProfile = new HashMap<>();
        userProfile.put("userId", user.getId());
        userProfile.put("username", user.getUsername());
        userProfile.put("email", user.getEmail());
        userProfile.put("password", user.getPassword());
        userProfile.put("role", user.getRole());

        if (employee != null) {
            userProfile.put("employeeId", employee.getEmployeeId());
            userProfile.put("employeeName", employee.getFirstName()+" "+employee.getLastName());
            userProfile.put("employeeDepartment", employee.getDepartmentName());
            userProfile.put("employeePosition", employee.getPosition());
        }

        return userProfile;
    }
}
