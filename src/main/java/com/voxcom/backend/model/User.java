package com.voxcom.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id  // Primary key → email
    private String email;

    private String name;

    private int totalTasks;
    private int completedCount;
    private int incompleteCount;

    // ---------- Constructors ----------
    public User() {}

    public User(String email, String name) {
        this.email = email;
        this.name = name;
        this.totalTasks = 0;
        this.completedCount = 0;
        this.incompleteCount = 0;
    }

    // ---------- Getters & Setters ----------
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public int getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    public int getIncompleteCount() {
        return incompleteCount;
    }

    public void setIncompleteCount(int incompleteCount) {
        this.incompleteCount = incompleteCount;
    }
}
