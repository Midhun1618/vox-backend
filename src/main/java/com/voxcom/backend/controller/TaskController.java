package com.voxcom.backend.controller;

import com.voxcom.backend.model.Task;
import com.voxcom.backend.model.User;
import com.voxcom.backend.repository.TaskRepository;
import com.voxcom.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;

    public TaskController(TaskRepository taskRepo, UserRepository userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    // ➕ ADD TASK
    @PostMapping
    public Task addTask(@RequestParam String email, @RequestBody String text) {
        Task task = new Task();
        task.setUserEmail(email);
        task.setText(text);
        task.setDone(false);

        User user = userRepo.findById(email).orElseThrow();
        user.setTotalTasks(user.getTotalTasks() + 1);
        userRepo.save(user);

        return taskRepo.save(task);
    }

    // ✅ MARK DONE
    @PutMapping("/{id}/done")
    public Task markDone(@PathVariable String id) {
        System.out.println("Marking task as done: " + id);

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.isDone()) {
            task.setDone(true);

            User user = userRepo.findById(task.getUserEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setCompletedCount(user.getCompletedCount() + 1);
            userRepo.save(user);
        }

        return taskRepo.save(task);
    }

    // 📥 GET USER TASKS
    @GetMapping
    public List<Task> getTasks(@RequestParam String email) {
        return taskRepo.findByUserEmail(email);
    }
}
