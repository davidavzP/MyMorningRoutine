package com.example.mymorningroutine.popupeditmenu;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.example.mymorningroutine.R;

public class Popup_newDeadline extends DialogFragment {

    private TextView textDeadline;
    private TextView textTime;
    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_new_deadline, null);
        builder.setView(view)
                .setTitle("New Deadline")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String deadline = textDeadline.getText().toString();
                        String time = textTime.getText().toString();
                        listener.applyTexts(deadline, time);

                    }
                });

        textDeadline = view.findViewById(R.id.edit_newDeadline);
        textTime = view.findViewById(R.id.edit_newDeadlineTime);


        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    public interface DialogListener{
        void applyTexts(String deadline, String time);

    }
}
