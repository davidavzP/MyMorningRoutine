package com.example.mymorningroutine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);

        final ArrayList<String> test = new ArrayList<>();

        for(int i = 0; i< 15; i++){
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
}
