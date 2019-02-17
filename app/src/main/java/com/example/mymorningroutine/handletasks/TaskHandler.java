package com.example.mymorningroutine.handletasks;


import android.content.Context;
import android.util.Log;

import android.view.Gravity;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;


import com.example.mymorningroutine.MainActivity;
import com.example.mymorningroutine.inputoutput.PlaySound;

import java.util.ArrayList;

public class TaskHandler extends MainActivity {

    private static String TAG = "TASKHANDLER";
    private static TaskHandler instance;
    private TaskQueue taskQueue;
    private TextView taskView;
    private TextView taskTimeView;
    private boolean completedTasks;
    private Button startTasks;
    private Task currentTask;
    private TaskTimer currentTimer;
    private Context mContext;
    private PlaySound playSound;



    public static TaskHandler get() {
        if (instance == null) {
            instance = new TaskHandler();

        }
        return instance;
    }

    public void getContext(Context mContext){
        this.mContext = mContext;
        playSound = new PlaySound(mContext);
    }

    public Task getCurrentTask(){
        return currentTask;
    }

    private TaskHandler(){
        taskQueue = new TaskQueue();
        completedTasks = true;

    }


    public void populateQueue(ArrayList<Task> taskList){
        taskQueue.flush();
        for(Task task: taskList){
            taskQueue.push(task);
        }
    }


    public void setButton(Button startTasks){
        this.startTasks = startTasks;
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
        completedTasks = false;
        runNextTask();
    }


    private void runNextTask(){

        if(taskQueue.len() != 0){
            startNewTask();
        }else {
            endAllTasks();
        }
    }

    private void endAllTasks(){
        showDonewithTasks();
        resetButton();
        playSound.playSound();
        displayToast();
    }

    private void startNewTask(){
        getNextTask();
        runTaskTimer();
        currentTask = taskQueue.pop();
    }

    private void resetButton(){
        completedTasks = true;
        startTasks.setClickable(true);
    }

    private void displayToast(){
        Toast toast = Toast.makeText(mContext, "DONE WITH ALL TASKS", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);

        toast.show();
    }

    private void showDonewithTasks(){
        currentTask.setRunning(false);
        String done = "DONE";
        taskView.setText(done);
    }

    private void getNextTask(){
        currentTask = taskQueue.peek();
        currentTask.setRunning(true);
        taskView.setText(currentTask.getTaskName());
    }

    private void runTaskTimer(){
        currentTimer = new TaskTimer(currentTask, taskTimeView);
        currentTimer.startTimer();
    }
    public boolean isCompleted(){
        return completedTasks;
    }

    public void stopAlert(){
        playSound.stopSound();
    }

    public void resetTasks(){
        currentTask.setRunning(false);
        completedTasks = true;
        currentTimer.resetTimer();
    }

    public void resetDisplay(){
        currentTask = taskQueue.peek();
        showcurrentTaskTime();
        currentTimer.showStartTime();

    }

    private void showcurrentTaskTime(){
        currentTimer = new TaskTimer(currentTask, taskTimeView);
        taskView.setText(taskQueue.peek().getTaskName());
    }

    public Task peekQueue(){
        return taskQueue.peek();
    }


}
