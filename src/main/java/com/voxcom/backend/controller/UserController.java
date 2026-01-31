package com.voxcom.backend.controller;

import com.voxcom.backend.model.User;
import com.voxcom.backend.dto.UserStatsResponse;
import com.voxcom.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    // 🔥 FIXED PATH
    @GetMapping("/by-email/{email}")
    public User getUser(@PathVariable String email) {
        return userRepository.findById(email).orElse(null);
    }

   // ✅ STATS ENDPOINT (DEBUG VERSION)
    @GetMapping("/stats")
    public UserStatsResponse getUserStats(@RequestParam String email) {

        User user = userRepository.findById(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found for email: " + email
                        )
                );

        return new UserStatsResponse(
                user.getName(),
                user.getEmail(),
                user.getTotalTasks(),
                user.getCompletedCount(),
                user.getIncompleteCount()
        );
    }
}