package com.example.mymorningroutine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymorningroutine.popupeditmenu.Popup_myWeek;
import com.example.mymorningroutine.popupeditmenu.Popup_newDeadline;
import com.example.mymorningroutine.popupeditmenu.Popup_newTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener,
        Popup_newDeadline.DialogListener, Popup_newTask.DialogListener, Popup_myWeek.DialogListener {

    private ListView listView;

    private TextView textDeadline;
    private TextView timeText;
    private File filedir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaultTexts();
        createlistView();
        filedir = getFilesDir();
    }

    public void setDefaultTexts(){
        textDeadline = findViewById(R.id.textDeadline);
        textDeadline.setText("THE DEADLINE!");
        timeText = findViewById(R.id.textTime);
        timeText.setText("TIME REMAINING");
    }

    public void createlistView(){
        listView = (ListView)findViewById(R.id.listView);


        final ArrayList<String> test = new ArrayList<>();

        for(int i = 0; i< 25; i++){
            test.add("Task that needs to be done-" + i);
        }

        ////code created by Ayaz Qureshi for channel InsideAndroid dated 19-oct-2017
        //https://www.youtube.com/watch?v=Mja5YoL9Jak
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,test);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"clicked item:"+i+" "+test.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });
        //end used code by: Ayaz Qureshi

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
    public void applyDeadline(String deadline, int minutes, int hours) {
            textDeadline.setText(deadline);
            String time = Integer.toString(hours) + ": " + Integer.toString(minutes);
            timeText.setText(time);
    }

    @Override
    public void applyTasks(String newTask, String hours, String minutes, String seconds) {
        //TODO:: applyTasks to listView
    }

    @Override
    public void applyMyWeek(HashMap<String, Boolean> myWeek) {
        //TODO:: applyMyWeek to overall Calendar
    }
}
