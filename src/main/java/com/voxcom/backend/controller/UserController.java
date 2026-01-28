package com.voxcom.backend.controller;

import com.voxcom.backend.model.User;
import com.voxcom.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setTotalTasks(0);
        user.setCompletedCount(0);
        user.setIncompleteCount(0);
        return userRepository.save(user);
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) {
        return userRepository.findById(email).orElse(null);
    }
}
