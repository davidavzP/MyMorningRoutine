package com.example.mymorningroutine.popupeditmenu;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;


import com.example.mymorningroutine.R;
import com.example.mymorningroutine.Singleton;

public class Popup_newDeadline extends DialogFragment {

    private TextView textDeadline;
    private DialogListener listener;
    private TimePicker timePicker;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());



       LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_new_deadline, null);
        builder.setView(view)
                .setTitle("Create New Deadline")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String deadline = textDeadline.getText().toString();
                        int[] hoursandminutes = versionControl();

                        listener.applyDeadline(deadline, hoursandminutes[0], hoursandminutes[1]);

                    }
                });

        textDeadline = view.findViewById(R.id.edit_newDeadline);
        timePicker = view.findViewById(R.id.timePicker);




        return builder.create();

    }

    public int[] versionControl(){
        int[] hoursandminutes = new int[2];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hoursandminutes[0] = timePicker.getHour();
            hoursandminutes[1] = timePicker.getMinute();
        }else {
            hoursandminutes[0] = timePicker.getCurrentHour();
            hoursandminutes[1] = timePicker.getCurrentMinute();
        }
        return hoursandminutes;

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
        void applyDeadline(String deadline, int minutes, int hours);

    }
}
