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


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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


                    }
                });




        return builder.create();

    }
    public void onCheckboxClicked(View view) {
        HashMap<CheckBox, Boolean> theWeek = new HashMap<CheckBox, Boolean>();

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.c_Monday:

                if (checked) {
                    
                }else {
                    // Remove the meat
                }
                break;

        }
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
        void applyMyWeek();
    }
}
