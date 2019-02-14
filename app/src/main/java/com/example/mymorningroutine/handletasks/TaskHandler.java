package com.example.mymorningroutine.handletasks;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mymorningroutine.inputoutput.Singleton;

import java.util.ArrayList;

public class TaskHandler {

    private static String TAG = "TASKHANDLER";
    private static TaskHandler instance;
    private TaskQueue taskQueue;
    private TextView taskView;
    private TextView taskTimeView;
    private boolean completedTasks;

    public static TaskHandler get() {
        if (instance == null) {
            instance = new TaskHandler();

        }
        return instance;
    }

    private TaskHandler(){
        taskQueue = new TaskQueue();
        completedTasks = false;
    }

    public void populateQueue(ArrayList<Task> taskList){
        for(Task task: taskList){
            taskQueue.push(task);
        }


    }

    public void setTaskView(TextView taskView){
        this.taskView = taskView;
    }

    public void setTaskTimeView(TextView taskTimeView){
        this.taskTimeView = taskTimeView;
    }

    public void startAllTasks(){
        completedTasks = false;
        runNextTask();
    }

    public void pushtoQueue(Task task){
        taskQueue.push(task);
        Log.d(TAG, "ADDED");
    }

    public void timeforNextTask(){
        PopupWindow popupWindow = new PopupWindow();
        runNextTask();
    }

    private void runNextTask(){
        Log.d(TAG, "DONE WITH TIMER");
        if(taskQueue.len() != 0){
            Task task = taskQueue.pop();
            taskView.setText(task.getTaskName());
            TaskTimer timer = new TaskTimer(task, taskTimeView);
            timer.startTimer();
        }else{
            String done = "DONE WITH ALL TASKS";
            taskView.setText(done);
            completedTasks = true;
        }
    }

    public boolean isCompletedTasks(){
        return completedTasks;
    }

}
