package com.voxcom.backend.controller;

import com.voxcom.backend.model.Task;
import com.voxcom.backend.model.User;
import com.voxcom.backend.repository.TaskRepository;
import com.voxcom.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Task addTask(@RequestBody Task task) {

        User user = userRepository.findById(task.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setTotalTasks(user.getTotalTasks() + 1);
        userRepository.save(user);

        task.setDone(false);
        return taskRepository.save(task);
    }

    @GetMapping("/{email}")
    public List<Task> getTasks(@PathVariable String email) {
        return taskRepository.findByUserEmail(email);
    }

    @PutMapping("/{id}/done")
    public Task markDone(@PathVariable String id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        User user = userRepository.findById(task.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setCompletedCount(user.getCompletedCount() + 1);
        userRepository.save(user);

        taskRepository.delete(task);
        return task;
    }
}
