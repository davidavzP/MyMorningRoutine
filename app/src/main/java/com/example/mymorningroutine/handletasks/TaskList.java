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

    public TaskList(File taskdir) throws FileNotFoundException {
        this.taskdir = taskdir;
        this.alltaskFiles = new HashMap<>();
        this.allTasks = new ArrayList<>();
        getAllTasksfromParent();
    }

    public void getAllTasksfromParent() throws FileNotFoundException {
        Singleton spoint = Singleton.get();
        if(taskdir.listFiles() != null){
            for(File f: taskdir.listFiles()){
                String taskname = f.getName();
                Task task = spoint.getTask(taskname);
                alltaskFiles.put(task, f);
                allTasks.add(task);
            }
        }

    }

    public ArrayList<Task> getAllTasks(){
        return allTasks;
    }

    public void addTask(Task task){
        allTasks.add(task);
        alltaskFiles.put(task, new File(task.getFileName()));
    }


}
