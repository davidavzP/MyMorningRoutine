package com.example.mymorningroutine.popupeditmenu;

import android.app.Activity;
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

import java.util.HashMap;


public class Popup_myWeek extends DialogFragment {


    private DialogListener listener;
    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private CheckBox sunday;
    private HashMap<String, Boolean> myWeek;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        myWeek = new HashMap<>();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_my_week, null);
        builder.setView(view)
                .setTitle("My Week")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myWeek.put(monday.getText().toString(), monday.isChecked());
                        myWeek.put(tuesday.getText().toString(), tuesday.isChecked());
                        myWeek.put(wednesday.getText().toString(), wednesday.isChecked());
                        myWeek.put(thursday.getText().toString(), thursday.isChecked());
                        myWeek.put(friday.getText().toString(), friday.isChecked());
                        myWeek.put(saturday.getText().toString(), saturday.isChecked());
                        myWeek.put(sunday.getText().toString(), sunday.isChecked());
                        listener.applyMyWeek(myWeek);
                    }
                });
                monday = view.findViewById(R.id.c_Monday);
                tuesday = view.findViewById(R.id.c_Tuesday);
                wednesday = view.findViewById(R.id.c_Wednesday);
                thursday = view.findViewById(R.id.c_Thursday);
                friday = view.findViewById(R.id.c_Friday);
                saturday = view.findViewById(R.id.c_Saturday);
                sunday = view.findViewById(R.id.c_Sunday);



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
        void applyMyWeek(HashMap<String, Boolean> myWeek);
    }
}
