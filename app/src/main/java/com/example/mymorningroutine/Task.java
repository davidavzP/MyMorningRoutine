package com.example.mymorningroutine;

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

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskHours(String taskHours) {
        this.taskHours = taskHours;
    }

    public void setTaskMinutes(String taskMinutes) {
        this.taskMinutes = taskMinutes;
    }

    public void setTaskSeconds(String taskSeconds) {
        this.taskSeconds = taskSeconds;
    }
}
