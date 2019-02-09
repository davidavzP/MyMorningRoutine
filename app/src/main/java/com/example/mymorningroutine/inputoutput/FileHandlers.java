package com.example.mymorningroutine.inputoutput;

import com.example.mymorningroutine.handleobjects.Deadline;
import com.example.mymorningroutine.handleobjects.TheWeek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandlers {

    private File filedir;

    public void set_filedir(File filedir){
        this.filedir = filedir;
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

}
