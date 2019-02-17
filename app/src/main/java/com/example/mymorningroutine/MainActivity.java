package com.example.mymorningroutine;



import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
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
import com.example.mymorningroutine.handletasks.TaskTimer;
import com.example.mymorningroutine.inputoutput.Singleton;
import com.example.mymorningroutine.popupeditmenu.Popup_editTask;
import com.example.mymorningroutine.popupeditmenu.Popup_myWeek;
import com.example.mymorningroutine.popupeditmenu.Popup_newDeadline;
import com.example.mymorningroutine.popupeditmenu.Popup_newTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


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
        setUpItems();
        setUpDisplayItems();

    }
    private void setUpItems(){
        setUpSingleton();
        setDeadlineTexts();
        setUpHandler();
        createlistView();
    }

    private void setUpDisplayItems(){
        setUpResetButton();
        findCurrentTaskInfo();
        handleEmptyTaskList();
        setCurrentDateStatus();
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
        //timeText = findViewById(R.id.textTime);
    }

    private void fillDeadlineTexts() throws FileNotFoundException{
        textDeadline.setText(spoint.getDeadline().getDeadline());
        //String time = spoint.getDeadline().getHours() + ": " + spoint.getDeadline().getMinutes();
        //timeText.setText(time);
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
    }

    @Override
    public void applyTasks(String newTask, String hours, String minutes, String seconds) {
        Task task = new Task(newTask, hours, minutes, seconds);
       checkTaskProperties(task);
       handleEmptyTaskList();
       setCurrentDateStatus();



    }

    private void checkTaskProperties(Task task){
        if(spoint.isExistingTask(task)){
            Toast.makeText(this, "That is Already a Task!", Toast.LENGTH_SHORT).show();
        }else if (task.getTaskHours().equals("0") && task.getTaskMinutes().equals("0")
        && task.getTaskSeconds().equals("0")){
            Toast.makeText(this, "You must have a time more than 0 seconds", Toast.LENGTH_SHORT).show();
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
        setCurrentDateStatus();
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
    public void deleteTask(Task task) {
        if(taskHandler.isCompleted()){
            spoint.deleteTask(task);
            task_List.removeTask(task);
            adapter.notifyDataSetChanged();
            handleEmptyTaskList();
            setCurrentDateStatus();

        }else {
            Toast.makeText(MainActivity.this, "You cannot delete tasks as they are running", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleEmptyTaskList(){
        if(task_List.getAllTasks().isEmpty()) {
            currentTask.setText("No Current Tasks");
            currentTime.setText("00:00");
        }else{
           Task task = task_List.getAllTasks().get(0);
           currentTask.setText(task.getTaskName());
           showTaskStartTime(task);
        }
    }

    private void showTaskStartTime(Task task){
        TaskTimer timer = new TaskTimer(task, currentTime);
        timer.showStartTime();
    }

    private void displayCurrentTask(){
        //findCurrentTaskInfo();
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


    private void setCurrentDateStatus(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        String dayofWeek= dayFormat.format(calendar.getTime());
        boolean isDay = false;
        try {
            isDay = spoint.getWeek().shouldTaskBeDone(dayofWeek);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        displayScheduleStatus(isDay);

    }

    private void displayScheduleStatus(boolean isDay){
        if(!isDay || task_List.getAllTasks().isEmpty()){
            setTextViewDate("THERE NO ARE TASKS TO BE DONE TODAY");

        }else{
            setTextViewDate("THERE ARE TASKS TO BE DONE TODAY");

        }
    }

    private void setTextViewDate(String text){
        TextView textViewDate = findViewById(R.id.calendar_date);
        textViewDate.setText(text);
    }

}
