package com.example.mymorningroutine.handletasks;

import android.util.Log;

import com.example.mymorningroutine.inputoutput.Singleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {

    private static final String TAG = "TASKLIST";
    private HashMap<Task, File> alltaskFiles;
    private ArrayList<Task> allTasks;
    private File taskdir;
    private Singleton spoint;

    public TaskList(File taskdir) throws FileNotFoundException {
        this.taskdir = taskdir;
        this.alltaskFiles = new HashMap<>();
        this.allTasks = new ArrayList<>();
        getAllTasksfromParent();
    }

    public void getAllTasksfromParent() throws FileNotFoundException {
        spoint = Singleton.get();
        if(taskdir.listFiles() != null){
            loopOverExistingTasks();
        }

    }

    private void loopOverExistingTasks() throws FileNotFoundException{
        for(File f: taskdir.listFiles()){
            Task task = createnewTask(f);
            alltaskFiles.put(task, f);
            allTasks.add(task);
            spoint.pushTaskQueue(task);
        }
    }


    private Task createnewTask(File f) throws FileNotFoundException {
        String taskname = f.getName();
        Task task = spoint.getTask(taskname);
        return task;
    }



    public ArrayList<Task> getAllTasks(){
        runTimer();
        return allTasks;
    }

    private void runTimer() {
        if(!allTasks.isEmpty()){
            Log.d(TAG, "IS NOT EMPTY");
            Task runningtask = allTasks.get(0);
            runningtask.setRunning(true);
        }

    }

    public void addTask(Task task){
        allTasks.add(task);
        alltaskFiles.put(task, new File(task.getFileName()));
    }


}
