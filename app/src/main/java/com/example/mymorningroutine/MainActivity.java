package com.example.mymorningroutine;



import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymorningroutine.handleobjects.Deadline;
import com.example.mymorningroutine.handleobjects.TheWeek;
import com.example.mymorningroutine.handletasks.CustomListViewAdapter;
import com.example.mymorningroutine.handletasks.Task;
import com.example.mymorningroutine.handletasks.TaskHandler;
import com.example.mymorningroutine.handletasks.TaskList;
import com.example.mymorningroutine.inputoutput.Singleton;
import com.example.mymorningroutine.popupeditmenu.Popup_editTask;
import com.example.mymorningroutine.popupeditmenu.Popup_myWeek;
import com.example.mymorningroutine.popupeditmenu.Popup_newDeadline;
import com.example.mymorningroutine.popupeditmenu.Popup_newTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener,
        Popup_newDeadline.DialogListener, Popup_newTask.DialogListener, Popup_myWeek.DialogListener, Popup_editTask.DialogListener {

    private static String TAG = "MAINACTIVITY";
    private ListView listView;
    private TextView textDeadline;
    private TextView timeText;
    private TaskList task_List;
    private File filedir;
    private Singleton spoint;
    private TextView currentTask;
    private TextView currentTime;
    private TaskHandler taskHandler;
    private Button resetTask;
    private Button startTasks;
    private CustomListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpSingleton();

        setDeadlineTexts();

        setUpHandler();

        createlistView();

        setUpResetButton();
    }

    private void setUpSingleton(){
        createSingleton();

        spoint = createSingleton();
    }


    private void setUpResetButton(){
        resetTask = findViewById(R.id.resetTasks);
        resetTask.setVisibility(View.INVISIBLE);
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

    private void setUpHandler(){
        taskHandler = TaskHandler.get();
        createHandler();
    }

    private void createHandler(){
        startTasks = findViewById(R.id.startTasks);
        taskHandler.setButton(startTasks);
        taskHandler.getContext(this);
    }



    private void setDeadlineTexts() {
        findDeadlineTexts();
        try {
            fillDeadlineTexts();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void findDeadlineTexts(){
        textDeadline = findViewById(R.id.textDeadline);
        timeText = findViewById(R.id.textTime);
    }

    private void fillDeadlineTexts() throws FileNotFoundException{
        textDeadline.setText(spoint.getDeadline().getDeadline());
        String time = spoint.getDeadline().getHours() + ": " + spoint.getDeadline().getMinutes();
        timeText.setText(time);
    }

    private void createlistView() {
        //TODO: clean
        try {
            task_List = new TaskList(spoint.getTaskdir());
            taskHandler.populateQueue(task_List.getAllTasks());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        createlistViewAdapter();
        createOnClicklistView();


    }

    private void createlistViewAdapter(){
        listView = findViewById(R.id.listView);

        adapter = new CustomListViewAdapter(this,
                R.layout.list_view_tasks, task_List.getAllTasks());

        listView.setAdapter(adapter);
    }

    private void createOnClicklistView(){
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Popup_editTask edit_task = new Popup_editTask(task_List.getAllTasks().get(position));
                edit_task.show(getSupportFragmentManager(), "editTask");
            }
        });
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
        //TODO: clean
        try {
            spoint.setDeadline(deadline);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, deadline.getDeadline());
        textDeadline.setText(deadline.getDeadline());
        String hours = lessThanTen(deadline.getHours());
        String minutes = lessThanTen(deadline.getMinutes());
        String time =  hours + ": " + minutes;
        deadline.setHour(hours);
        deadline.setMinutes(minutes);
        timeText.setText(time);


    }

    public String lessThanTen(String num){
        //TODO: delete this
        if(FixbugFor24(num)){return num = "24";};
        int num_int = Integer.valueOf(num);
        if(num_int < 10){
            return num = "0" + num;
        }else{
            return num;
        }
    }

    public boolean FixbugFor24(String num){
        //TODO: delete this
        if(num.equals("0") || num.equals("00")){
            return true;
        }
        return false;
    }

    @Override
    public void applyTasks(String newTask, String hours, String minutes, String seconds) {

        //TODO: clean
        Task task = new Task(newTask, hours, minutes, seconds);
       checkTaskProperties(task);


    }

    private void checkTaskProperties(Task task){
        if(spoint.isExistingTask(task)){
            Toast.makeText(this, "That is Already a Task!", Toast.LENGTH_SHORT).show();
        }else {
            taskHandler.pushtoQueue(task);
            writeTask(task);
            putToTaskList(task);
        }
    }

    private void writeTask(Task task){
        try {
            spoint.writeTask(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putToTaskList(Task task){
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
        //TODO: clean
            if(task_List.getAllTasks().size() > 0) {
                setButtonVisiability(true);
                taskHandler.populateQueue(task_List.getAllTasks());
                displayCurrentTask();
                taskHandler.startAllTasks();
            }else{
                Toast.makeText(MainActivity.this, "There are no tasks to do", Toast.LENGTH_SHORT).show();
            }

    }

    @Override
    public void applyTexts(Task task) {
        if(taskHandler.isCompleted()){
            spoint.deleteTask(task);
            task_List.removeTask(task);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(MainActivity.this, "You cannot delete tasks as they are running", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayCurrentTask(){
        findCurrentTaskInfo();
        setTaskHandlerView();
    }

    private void setTaskHandlerView(){
        taskHandler.setTaskView(currentTask);
        taskHandler.setTaskTimeView(currentTime);
    }

    private void findCurrentTaskInfo(){
        currentTask = findViewById(R.id.currentTask);
        currentTime = findViewById(R.id.currentTime);
    }

    private void setButtonVisiability(boolean bool){
        if(bool){
            startTasks.setVisibility(View.INVISIBLE);
            resetTask.setVisibility(View.VISIBLE);
        }else {
            startTasks.setVisibility(View.VISIBLE);
            resetTask.setVisibility(View.INVISIBLE);
        }

    }
    public void resetTasks(View view) {

        taskHandler.stopAlert();
        if(task_List.getAllTasks().size() > 0){
            resetTaskHandler();
        }
        setButtonVisiability(false);

    }

    private void resetTaskHandler(){
        taskHandler.resetTasks();
        taskHandler.populateQueue(task_List.getAllTasks());
        taskHandler.resetDisplay();
    }





}
