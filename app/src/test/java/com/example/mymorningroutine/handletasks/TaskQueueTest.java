package com.example.mymorningroutine.handletasks;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Optional;

import static org.junit.Assert.*;

public class TaskQueueTest {

    @Test
    public void PopandPush() {
        TaskQueue queue = new TaskQueue();
        Task task1 = new Task("A", "1", "1", "1");
        Task task2 = new Task("B", "2", "2", "2");
        Task task3 = new Task("C", "3", "3", "3");
        Task task4 = new Task("D", "4", "4", "4");
        Task task5 = new Task("E", "5", "5", "5");

        queue.push(task1);
        queue.push(task2);
        queue.push(task3);
        queue.push(task4);
        queue.push(task5);
        assertEquals(5, queue.len());
        assertEquals(task1, queue.pop());
        assertEquals(task2, queue.pop());
        assertEquals(task3, queue.pop());
        assertEquals(task4, queue.pop());
        assertEquals(task5, queue.pop());
        assertEquals(new Task("", "", "", ""), queue.pop());

    }

    @Test

    public void PeekandPop() {
        TaskQueue queue = new TaskQueue();
        Task task1 = new Task("A", "1", "1", "1");
        Task task2 = new Task("B", "2", "2", "2");
        Task task3 = new Task("C", "3", "3", "3");
        Task task4 = new Task("D", "4", "4", "4");
        Task task5 = new Task("E", "5", "5", "5");

        queue.push(task1);
        queue.push(task2);
        queue.push(task3);
        queue.push(task4);
        queue.push(task5);
        assertEquals(5, queue.len());
        assertEquals(task1, queue.peek());
        assertEquals(task1, queue.pop());
        assertEquals(task2, queue.peek());
        assertEquals(task2, queue.pop());
        assertEquals(task3, queue.peek());
        assertEquals(task3, queue.pop());
        assertEquals(task4, queue.peek());
        assertEquals(task4, queue.pop());
        assertEquals(task5, queue.peek());
        assertEquals(task5, queue.pop());
        assertEquals(new Task("", "", "", ""), queue.peek());
        assertEquals(new Task("", "", "", ""), queue.pop());
    }
}