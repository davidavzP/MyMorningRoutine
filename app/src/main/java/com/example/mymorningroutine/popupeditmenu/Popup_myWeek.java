package com.example.mymorningroutine.popupeditmenu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.example.mymorningroutine.R;
import com.example.mymorningroutine.inputoutput.Singleton;
import com.example.mymorningroutine.handleobjects.TheWeek;

import java.io.FileNotFoundException;


public class Popup_myWeek extends DialogFragment {


    private DialogListener listener;
    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private CheckBox sunday;
    private TheWeek theWeek;
    private Singleton spoint;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        spoint = Singleton.get();

        try {
            theWeek = spoint.getWeek();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //System.out.println("MONDAY: " + theWeek.isMonday());


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_my_week, null);
        builder.setView(view);
        builder.setTitle("My Week");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                theWeek.setMonday(monday.isChecked());
                theWeek.setTuesday(tuesday.isChecked());
                theWeek.setWednesday(wednesday.isChecked());
                theWeek.setThursday(thursday.isChecked());
                theWeek.setFriday(friday.isChecked());
                theWeek.setSaturday(saturday.isChecked());
                theWeek.setSunday(sunday.isChecked());

                listener.applyMyWeek(theWeek);
            }
        });


        monday = view.findViewById(R.id.c_Monday);
        monday.setChecked(theWeek.isMonday());

        tuesday = view.findViewById(R.id.c_Tuesday);
        tuesday.setChecked(theWeek.isTuesday());

        wednesday = view.findViewById(R.id.c_Wednesday);
        wednesday.setChecked(theWeek.isWednesday());

        thursday = view.findViewById(R.id.c_Thursday);
        thursday.setChecked(theWeek.isThursday());

        friday = view.findViewById(R.id.c_Friday);
        friday.setChecked(theWeek.isFriday());

        saturday = view.findViewById(R.id.c_Saturday);
        saturday.setChecked(theWeek.isSaturday());

        sunday = view.findViewById(R.id.c_Sunday);
        sunday.setChecked(theWeek.isSunday());




        return builder.create();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface DialogListener{
        void applyMyWeek(TheWeek theWeek);
    }



}
