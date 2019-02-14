package com.example.mymorningroutine;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.mymorningroutine.handleobjects.Deadline;
import com.example.mymorningroutine.handleobjects.TheWeek;
import com.example.mymorningroutine.handletasks.CustomListViewAdapter;
import com.example.mymorningroutine.handletasks.Task;
import com.example.mymorningroutine.handletasks.TaskHandler;
import com.example.mymorningroutine.handletasks.TaskList;
import com.example.mymorningroutine.handletasks.TaskQueue;
import com.example.mymorningroutine.inputoutput.Singleton;
import com.example.mymorningroutine.popupeditmenu.Popup_myWeek;
import com.example.mymorningroutine.popupeditmenu.Popup_newDeadline;
import com.example.mymorningroutine.popupeditmenu.Popup_newTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener,
        Popup_newDeadline.DialogListener, Popup_newTask.DialogListener, Popup_myWeek.DialogListener {

    private ListView listView;

    private TextView textDeadline;
    private TextView timeText;
    private TaskList task_List;
    private File filedir;
    private Singleton spoint;
    private TextView currentTask;
    private TextView currentTime;
    private TaskHandler taskHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSingleton();
        spoint = createSingleton();
        setDeadlineTexts();
        taskHandler = TaskHandler.get();
        createlistView();

    }



    public Singleton createSingleton(){
        filedir = getFilesDir();
        Singleton s = Singleton.get();
        try {
            s.first_instance_getFiles(filedir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }


    public void setDeadlineTexts() {
        textDeadline = findViewById(R.id.textDeadline);
        timeText = findViewById(R.id.textTime);
        try {
            textDeadline.setText(spoint.getDeadline().getDeadline());
            String time = spoint.getDeadline().getHours() + ": " + spoint.getDeadline().getMinutes();
            timeText.setText(time);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void createlistView(){

        try {

            task_List = new TaskList(spoint.getTaskdir());
            //TODO: Correctly create the taskQueue
            taskHandler.populateQueue(task_List.getAllTasks());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        listView = findViewById(R.id.listView);

        CustomListViewAdapter adapter = new
                CustomListViewAdapter(this,
                R.layout.list_view_tasks, task_List.getAllTasks());

        listView.setAdapter(adapter);

    }
    //method call exits in the xml file
    public void showPopup(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.nav_button);
        popupMenu.show();
    }

    private void openPopUpNewTask(){
        Popup_newTask newTask = new Popup_newTask();
        newTask.show(getSupportFragmentManager(), "newTask");

    }

    private void openPopUpMyWeek(){
        Popup_myWeek myWeek = new Popup_myWeek();
        myWeek.show(getSupportFragmentManager(), "myWeek");
    }

    private void openPopUpNewDeadline(){
        Popup_newDeadline newDeadline = new Popup_newDeadline();
        newDeadline.show(getSupportFragmentManager(), "newDeadline");
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_newTask:
                openPopUpNewTask();
                return true;
            case R.id.nav_newDeadline:
                openPopUpNewDeadline();
                return true;
            case R.id.nav_myWeek:
                openPopUpMyWeek();
                return true;
            default:
                return false;

        }

    }

    @Override
    public void applyDeadline(Deadline deadline) {
        try {
            spoint.setDeadline(deadline);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textDeadline.setText(deadline.getDeadline());
        String hours = lessThanTen(deadline.getHours());
        String minutes = lessThanTen(deadline.getMinutes());
        String time =  hours + ": " + minutes;
        deadline.setHour(hours);
        deadline.setMinutes(minutes);
        timeText.setText(time);


    }

    public String lessThanTen(String num){
        if(FixbugFor24(num)){return num = "24";};
        int num_int = Integer.valueOf(num);
        if(num_int < 10){
            return num = "0" + num;
        }else{
            return num;
        }
    }

    public boolean FixbugFor24(String num){
        if(num.equals("0") || num.equals("00")){
            return true;
        }
        return false;
    }

    @Override
    public void applyTasks(String newTask, String hours, String minutes, String seconds) {
        Task task = new Task(newTask, hours, minutes, seconds);
        //TODO: MAKE SURE THAT WE DONT OVERRIDE FILE INFO
        //TODO: correctly update the taskQueue
        taskHandler.pushtoQueue(task);
        try {
            spoint.writeTask(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            task_List.addTask(spoint.readTask(task.getFileName()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void applyMyWeek(TheWeek theWeek) {
        try {
            spoint.setWeek(theWeek);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    //method call exists in the xml file
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startTasks(View view) {
        if(taskHandler.isCompletedTasks()){
            taskHandler.populateQueue(task_List.getAllTasks());
            view.setClickable(true);
        }else {
            view.setClickable(false);
            currentTask = findViewById(R.id.currentTask);
            currentTime = findViewById(R.id.currentTime);
            taskHandler.setTaskView(currentTask);
            taskHandler.setTaskTimeView(currentTime);
            taskHandler.startAllTasks();
        }


    }
}
