package com.example.mymorningroutine.handletasks;

public class Task {
    private String taskName;
    private String taskHours;
    private String taskMinutes;
    private String taskSeconds;

    public Task(String taskName, String taskHours, String taskMinutes, String taskSeconds) {
        this.taskName = taskName;
        this.taskHours = taskHours;
        this.taskMinutes = taskMinutes;
        this.taskSeconds = taskSeconds;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskHours() {
        return taskHours;
    }

    public String getTaskMinutes() {
        return taskMinutes;
    }

    public String getTaskSeconds() {
        return taskSeconds;
    }

}
