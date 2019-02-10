package com.example.mymorningroutine.handletasks;

import com.example.mymorningroutine.inputoutput.Singleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {

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
        }
    }


    private Task createnewTask(File f) throws FileNotFoundException {
        String taskname = f.getName();
        Task task = spoint.getTask(taskname);
        return task;
    }

    public ArrayList<Task> getAllTasks(){
        return allTasks;
    }

    public void addTask(Task task){
        allTasks.add(task);
        alltaskFiles.put(task, new File(task.getFileName()));
    }


}
