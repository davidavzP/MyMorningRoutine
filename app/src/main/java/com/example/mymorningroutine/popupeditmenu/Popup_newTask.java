package com.example.mymorningroutine.popupeditmenu;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.mymorningroutine.R;
import com.example.mymorningroutine.handletasks.Task;
import com.example.mymorningroutine.inputoutput.Singleton;

import java.io.IOException;


public class Popup_newTask extends DialogFragment {
    private static String TAG = "POPUPNEWTASK";
    private EditText newTaskname;
    private DialogListener listener;
    private NumberPicker hoursPicker;
    private NumberPicker minutesPicker;
    private NumberPicker secondsPicker;



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


                        String hours = String.valueOf(hoursPicker.getValue());
                        String minutes = String.valueOf(minutesPicker.getValue());
                        String seconds = String.valueOf(secondsPicker.getValue());



                        if(newTaskname.getText().length() == 0){
                            Toast.makeText(getContext(), "Task Name can't be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            listener.applyTasks(newTask, hours, minutes, seconds);
                        }

                    }
                });

        newTaskname = view.findViewById(R.id.edit_newTask);


        hoursPicker = setPicker(view, hoursPicker, R.id.hoursPicker);
        minutesPicker = setPicker(view, minutesPicker, R.id.minutesPicker);
        secondsPicker = setPicker(view, secondsPicker, R.id.secondsPicker);

        return builder.create();

    }

    private NumberPicker setPicker(View view, NumberPicker picker, int id){
        picker = view.findViewById(id);
        picker.setMinValue(0);
        picker.setMaxValue(60);
        return picker;
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
