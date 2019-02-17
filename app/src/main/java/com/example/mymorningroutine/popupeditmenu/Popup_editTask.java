package com.example.mymorningroutine.popupeditmenu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.mymorningroutine.R;
import com.example.mymorningroutine.handletasks.Task;

@SuppressLint("ValidFragment")
public class Popup_editTask extends DialogFragment {
    private DialogListener listener;
    private Task task;
    private TextView textTask;

    @SuppressLint("ValidFragment")
    public Popup_editTask(Task task){
        this.task = task;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_task, null);

        builder.setView(view)
                .setTitle("Delete Task")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        listener.deleteTask(task);
                    }
                });
        textTask = view.findViewById(R.id.textTask);
        textTask.setText("Do you want to delete " + task.getTaskName());

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface DialogListener {
        void deleteTask(Task task);
    }
}
