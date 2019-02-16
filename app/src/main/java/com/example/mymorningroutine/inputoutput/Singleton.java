package com.example.mymorningroutine.inputoutput;

import com.example.mymorningroutine.handleobjects.Deadline;
import com.example.mymorningroutine.handleobjects.TheWeek;
import com.example.mymorningroutine.handletasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class Singleton {
    private static Singleton instance;
    private FileHandlers fileHandlers;
    private File filedir;
    private File taskListFolder;


    public static Singleton get() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private Singleton() {
        this.fileHandlers = new FileHandlers();
    }

    ///Only called once to set the file directory
    public void first_instance_getFiles(File dir) throws IOException {
        build_TaskList();
        this.filedir = dir;
        fileHandlers.set_filedir(filedir, taskListFolder);
        if(!isFile("TheWeek.txt")){
            build_weekFile();
        }
        if(!isFile("Deadline.txt")){
            build_deadlineFile();
        }
    }


    public void build_TaskList(){
        taskListFolder = build_taskListDir();
    }


    public boolean isFile(String name){
        return new File(filedir, name).exists();
    }

    public File getTaskdir(){
        return taskListFolder;
    }

    private void build_weekFile()throws IOException{
        String week = "Monday: false\nTuesday: false\nWednesday: false\nThursday: false\nFriday: false\nSaturday: false\nSunday: false";
        fileHandlers.writeFile_filedir("TheWeek.txt", week);
    }

    private void build_deadlineFile() throws IOException {
        String deadline = "Deadline\n9\n10";
        fileHandlers.writeFile_filedir("Deadline.txt", deadline);
    }

    private File build_taskListDir(){
        return fileHandlers.makeDir("TaskList");
    }

    public TheWeek getWeek() throws FileNotFoundException {
        TheWeek theweek = fileHandlers.read_TheWeek_file();
        return theweek;
    }


    public void setWeek(TheWeek week) throws IOException {
        fileHandlers.writeFile_filedir("TheWeek.txt", week.toString());
    }

    public Deadline getDeadline() throws FileNotFoundException{
        Deadline deadline = fileHandlers.read_Deadline_file();
        return deadline;
    }

    public void setDeadline(Deadline deadline) throws IOException {
        fileHandlers.writeFile_filedir("Deadline.txt", deadline.toString());
    }

    public void writeTask(Task task) throws IOException {

            fileHandlers.writeFile_taskdir(task.getFileName(), task.toString());
    }

    public Task readTask(String taskname) throws FileNotFoundException {
        return fileHandlers.read_Task_file(taskname);
    }

    public void deleteTask(Task task){
        fileHandlers.delete_Task_file(task.getFileName());
    }

    public boolean isExistingTask(Task task){
        if(fileHandlers.isTaskFile(task)){
            return true;
        }else {return false;}
    }






}
