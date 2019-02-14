package com.example.mymorningroutine.handletasks;




import java.util.ArrayDeque;



public class TaskQueue {

    private ArrayDeque<Task> taskQueue;


    //TODO: create a method for updating the TaskList
    public TaskQueue(){
        this.taskQueue = new ArrayDeque<>();
    }

    public void push(Task task){
        taskQueue.addLast(task);
    }


    public Task pop(){
        if(taskQueue.isEmpty()){
            return new Task("", "", "", "");
        }else {
            return taskQueue.removeFirst();
        }
    }


    public Task peek(){
        if(taskQueue.isEmpty()){
            return new Task("", "", "", "");
        }else {
            return taskQueue.peekFirst();
        }
    }

    public int len(){
        return taskQueue.size();
    }

}
