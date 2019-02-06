package com.example.mymorningroutine.popupeditmenu;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.mymorningroutine.R;


public class Popup_newTask extends DialogFragment {
    private EditText newTaskname;
    private EditText newTasktime;

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

                    }
                });

        newTaskname = view.findViewById(R.id.edit_newTask);
        newTasktime = view.findViewById(R.id.edit_newTaskTime);
        return builder.create();

    }
}
