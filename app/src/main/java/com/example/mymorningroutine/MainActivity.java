package com.example.mymorningroutine;

import android.content.Intent;
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

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    private ListView listView;

    private TextView textView;
    private TextView timeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: make deadline and time not just textViews
        textView = findViewById(R.id.textView);
        textView.setText("THE DEADLINE!");
        timeText = findViewById(R.id.textTime);
        timeText.setText("TIME REMAINING");

        createlistView();

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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_newTask:
                startpopupactivity(Popup_newTask.class);
                 return true;
            case R.id.nav_newDeadline:
              startpopupactivity(Popup_newDeadline.class);
                return true;
            case R.id.nav_myWeek:
                startpopupactivity(Popup_myWeek.class);
                return true;
            default:
                return false;

        }

    }

    private void startpopupactivity(Class popUpClass) {
        Intent popup_new_task = new Intent(MainActivity.this, popUpClass);
        startActivity(popup_new_task);


    }


}
