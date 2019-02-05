package com.example.mymorningroutine.popupeditmenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.mymorningroutine.R;

public class Popup_myWeek extends Activity {


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_my_week);



        setDisplayBoundsDefault();
    }



    public void setDisplayBoundsDefault(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.8));


    }

    //TODO: implement CheckBox Methods For Day of the Week
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.check_Monday:
                if(checked){
                    System.out.println("Monday Checked");
                }else{
                    System.out.println("Monday not Checked");
                }
        }



    }
}
