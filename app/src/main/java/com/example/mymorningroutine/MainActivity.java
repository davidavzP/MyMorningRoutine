package com.example.mymorningroutine;

import android.os.Bundle;
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
import com.example.mymorningroutine.handletasks.TaskList;
import com.example.mymorningroutine.inputoutput.Singleton;
import com.example.mymorningroutine.popupeditmenu.Popup_myWeek;
import com.example.mymorningroutine.popupeditmenu.Popup_newDeadline;
import com.example.mymorningroutine.popupeditmenu.Popup_newTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener,
        Popup_newDeadline.DialogListener, Popup_newTask.DialogListener, Popup_myWeek.DialogListener {

    private ListView listView;

    private TextView textDeadline;
    private TextView timeText;
    private ArrayList<Task> taskList;
    private TaskList task_List;
    private File filedir;
    private Singleton spoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSingleton();
        spoint = createSingleton();
        setDeadlineTexts();
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
        //TODO: UPDATE LISTVIEW FROM FILES
        try {
            task_List = new TaskList(spoint.getTaskdir());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        listView = (ListView)findViewById(R.id.listView);
        taskList = task_List.getAllTasks();
        //taskList = new ArrayList<>();
        CustomListViewAdapter adapter = new CustomListViewAdapter(this, R.layout.list_view_tasks, taskList);
        listView.setAdapter(adapter);

    }

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
        String time = deadline.getHours() + ": " + deadline.getMinutes();
        timeText.setText(time);


    }

    @Override
    public void applyTasks(String newTask, String hours, String minutes, String seconds) {
        //TODO:: applyTasks to listView
        Task task = new Task(newTask, hours, minutes, seconds);
        try {
            spoint.setTask(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            task_List.addTask(spoint.getTask(task.getFileName()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        Task newtask = new Task(newTask, hours, minutes, seconds);
//        taskList.add(newtask);
    }

    @Override
    public void applyMyWeek(TheWeek theWeek) {
        try {
            spoint.setWeek(theWeek);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
