package com.example.mymorningroutine.popupeditmenu;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.mymorningroutine.R;
import com.example.mymorningroutine.handletasks.Task;
import com.example.mymorningroutine.inputoutput.Singleton;

import java.io.IOException;


public class Popup_newTask extends DialogFragment {
    private EditText newTaskname;
    private EditText newTasktime;
    private EditText textSeconds;
    private EditText textMinutes;
    private EditText textHours;
    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_new_task, null);
        builder.setView(view)
                .setTitle("Create New Task")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newTask = newTaskname.getText().toString();
                        String hours = textHours.getText().toString();
                        String minutes = textMinutes.getText().toString();
                        String seconds = textSeconds.getText().toString();
                        listener.applyTasks(newTask, hours, minutes, seconds);
                    }
                });

        newTaskname = view.findViewById(R.id.edit_newTask);
        textSeconds = view.findViewById(R.id.edit_Seconds);
        textMinutes = view.findViewById(R.id.edit_Minutes);
        textHours = view.findViewById(R.id.edit_Hours);

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
        void applyTasks(String newTask, String hours, String minutes, String seconds);


    }
}
