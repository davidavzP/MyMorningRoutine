package com.example.mymorningroutine.handletasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void isEmpty() {
        Task task = new Task("", "", "", "");
        Assert.assertEquals(true, task.isEmpty());
        Task task1 = new Task("hello", "", "", "");
        Assert.assertEquals(false, task1.isEmpty());
    }

    @Test
    public void Running() {
        Task task1 = new Task("hello", "", "", "");
        Assert.assertEquals(false, task1.isRunning());
        task1.setRunning(true);
        Assert.assertEquals(true, task1.isRunning());
    }

    @Test
    public void parse() {
        Task task =  new Task("test", "0","0", "0");
        ArrayList<String> string = new ArrayList<>();
        string.add("test");
        string.add("0");
        string.add("0");
        string.add("0");
        Task task1 = Task.parse(string);
        Assert.assertEquals(task1, task);
    }

    @Test
    public void getFileName() {
        Task task =  new Task("test", "0","0", "0");
        Assert.assertEquals("test.txt", task.getFileName());
    }

    @Test
    public void getTaskName() {
        Task task =  new Task("test", "0","0", "0");
        Assert.assertEquals("test", task.getTaskName());
    }

    @Test
    public void getTaskHours() {
        Task task =  new Task("test", "1","0", "0");
        Assert.assertEquals("1", task.getTaskHours());
    }

    @Test
    public void getTaskMinutes() {
        Task task =  new Task("test", "1","2", "0");
        Assert.assertEquals("2", task.getTaskMinutes());
    }

    @Test
    public void getTaskSeconds() {
        Task task =  new Task("test", "1","2", "3");
        Assert.assertEquals("3", task.getTaskSeconds());
    }
}