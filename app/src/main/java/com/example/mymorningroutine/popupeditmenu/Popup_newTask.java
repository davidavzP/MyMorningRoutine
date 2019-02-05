package com.example.mymorningroutine.popupeditmenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.mymorningroutine.R;

public class Popup_newTask extends Activity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_new_task);

         DisplayMetrics dm = new DisplayMetrics();
         getWindowManager().getDefaultDisplay().getMetrics(dm);

         int width = dm.widthPixels;
         int height = dm.heightPixels;

         getWindow().setLayout((int)(width*.8), (int)(height*.8));

    }
}
