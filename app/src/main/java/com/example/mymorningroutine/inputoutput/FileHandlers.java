package com.example.mymorningroutine.inputoutput;

import com.example.mymorningroutine.handleobjects.Deadline;
import com.example.mymorningroutine.handleobjects.TheWeek;
import com.example.mymorningroutine.handletasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandlers {

    private File filedir;
    private File taskdir;

    public void set_filedir(File filedir, File taskdir){
        this.filedir = filedir;
        this.taskdir = taskdir;
    }


    //TODO:HANDLE ERROR
    public void write_to_file(String file_name, String text) throws IOException {
        File output = new File(filedir, file_name);

        PrintWriter pw = new PrintWriter(new FileWriter(output));

        pw.println(text);
        pw.close();


    }


    public TheWeek read_TheWeek_file() throws FileNotFoundException {

        ArrayList<String> tempweek = new ArrayList<>();
        Scanner x;

        x = new Scanner(new File(filedir, "TheWeek.txt"));

        while(x.hasNextLine()) {
            String line = x.nextLine();
            tempweek.add(line);

        }
        x.close();
        return new TheWeek().parseWeek(tempweek);

    }

    public Deadline read_Deadline_file() throws FileNotFoundException {
        ArrayList<String> tempdeadline = new ArrayList<>();
        Scanner x;

        x = new Scanner(new File(filedir, "Deadline.txt"));

        while(x.hasNextLine()) {
            String line = x.nextLine();
            tempdeadline.add(line);

        }
        x.close();

        return Deadline.parse(tempdeadline);

    }

    public Task read_Task_file(String taskname) throws FileNotFoundException {
        ArrayList<String> temptask = new ArrayList<>();
        Scanner x;

        x = new Scanner(new File(taskdir, taskname));

        while(x.hasNextLine()) {
            String line = x.nextLine();
            temptask.add(line);

        }
        x.close();

        return Task.parse(temptask);

    }

    public File makeDir(String namedir){
        File dir = new File(namedir);
        dir.mkdirs();

        return dir;
    }



}
