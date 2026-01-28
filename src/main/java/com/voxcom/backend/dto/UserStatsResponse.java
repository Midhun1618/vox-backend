package com.voxcom.backend.dto;

public class UserStatsResponse {

    private String name;
    private String email;

    private int totalTasks;
    private int completed;
    private int incomplete;
    private int activeTasks;

    private double completionRate;

    public UserStatsResponse(
            String name,
            String email,
            int totalTasks,
            int completed,
            int incomplete
    ) {
        this.name = name;
        this.email = email;
        this.totalTasks = totalTasks;
        this.completed = completed;
        this.incomplete = incomplete;
        this.activeTasks = totalTasks - completed - incomplete;
        this.completionRate = totalTasks == 0
                ? 0
                : (completed * 100.0) / totalTasks;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getTotalTasks() { return totalTasks; }
    public int getCompletedCount() { return completed; }
    public int getIncompleteCount() { return incomplete; }
    public int getActiveTasks() { return activeTasks; }
    public double getCompletionRate() { return completionRate; }
}
