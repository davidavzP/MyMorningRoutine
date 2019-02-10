package com.example.mymorningroutine.handletasks;


import java.util.ArrayList;

public class Task {
    private String taskName;
    private String taskHours;
    private String taskMinutes;
    private String taskSeconds;
    private boolean isRunning;

    public Task(String taskName, String taskHours, String taskMinutes, String taskSeconds) {
        this.taskName = taskName;
        this.taskHours = taskHours;
        this.taskMinutes = taskMinutes;
        this.taskSeconds = taskSeconds;
        this.isRunning = false;
    }

    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public boolean isRunning(){
        return isRunning;
    }

    public static Task parse(ArrayList<String> task){
        return new Task(task.get(0), task.get(1), task.get(2), task.get(3));
    }

    public String getFileName(){
        return taskName+".txt";
    }

    @Override
    public String toString(){
        return taskName + "\n" + taskHours + "\n" + taskMinutes + "\n" + taskSeconds;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            Task that = (Task) other;
            return this.taskName.equals(that.taskName);
        } else {
            return false;
        }
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
