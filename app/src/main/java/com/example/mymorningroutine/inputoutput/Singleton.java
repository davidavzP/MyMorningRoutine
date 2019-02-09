package com.example.mymorningroutine.inputoutput;

import com.example.mymorningroutine.handleobjects.Deadline;
import com.example.mymorningroutine.handleobjects.TheWeek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Singleton {
    private static Singleton instance;
    private FileHandlers fileHandlers;
    private File filedir;

    public static Singleton get() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    ///Only called once to set the file directory
    public void first_instance_getFiles(File dir) throws IOException {
        this.filedir = dir;
        fileHandlers.set_filedir(filedir);
        if(!isFile("TheWeek.txt")){
            build_weekFile();
        }
        if(!isFile("Deadline.txt")){
            build_deadlineFile();
        }
    }

    public boolean isFile(String name){
        return new File(filedir, name).exists();
    }


    private Singleton() {
        this.fileHandlers = new FileHandlers();
    }



    private void build_weekFile()throws IOException{
        String week = "Monday: false\nTuesday: false\nWednesday: false\nThursday: false\nFriday: false\nSaturday: false\nSunday: false";
        fileHandlers.write_to_file("TheWeek.txt", week);

    }

    private void build_deadlineFile() throws IOException {
        String deadline = "Deadline\n8\n10";
        fileHandlers.write_to_file("Deadline.txt", deadline);
    }



    public TheWeek getWeek() throws FileNotFoundException {
        TheWeek theweek = fileHandlers.read_TheWeek_file();
        return theweek;
    }


    public void setWeek(TheWeek week) throws IOException {
        fileHandlers.write_to_file("TheWeek.txt", week.toString());
    }

    public Deadline getDeadline() throws FileNotFoundException{
        Deadline deadline = fileHandlers.read_Deadline_file();
        return deadline;
    }

    public void setDeadline(Deadline deadline) throws IOException {
        fileHandlers.write_to_file("TheWeek.txt", deadline.toString());
    }






}
