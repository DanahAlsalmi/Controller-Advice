package com.example.capstone2.Controller;

import com.example.capstone2.Model.User;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //Done test - get
    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    //done test - Add User
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }

    //Done test - Update User
    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId, @Valid @RequestBody User user){
        userService.updateUser(userId, user);
        return ResponseEntity.status(200).body("User updated successfully");
    }

    //Done test - Delete User
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

    //Done test - Profile
    @GetMapping("/profile/{userId}")
    public ResponseEntity getUserProfile(@PathVariable Integer userId) {
        Map<String, Object> userProfile = userService.getUserProfile(userId);
        return ResponseEntity.status(200).body(userProfile);
    }

}

