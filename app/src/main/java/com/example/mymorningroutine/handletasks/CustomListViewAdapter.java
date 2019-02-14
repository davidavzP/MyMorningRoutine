package com.example.mymorningroutine.handletasks;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mymorningroutine.R;
import com.example.mymorningroutine.inputoutput.Singleton;

import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<Task> {

    private static final String TAG = "CUSTOMLISTVIEWADAPTER";
    private final List<Task> objects;
    private Context mContext;
    private int mResource;

    public CustomListViewAdapter(Context context, int resource, List<Task> objects) {

        super(context, resource, objects);
        this.mContext = context;
        this.mResource =resource;
        this.objects = objects;


    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String taskname = getItem(position).getTaskName();
        String taskhours = getItem(position).getTaskHours();
        String taskminutes = getItem(position).getTaskMinutes();
        String taskseconds = getItem(position).getTaskSeconds();


        Singleton spoint = Singleton.get();



        Task task = new Task(taskname, taskhours, taskminutes, taskseconds);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView taskName = convertView.findViewById(R.id.userTaskname);
        TextView taskTime = convertView.findViewById(R.id.userTime);


        taskName.setText(taskname);
        String time = taskhours + ": " + taskminutes + ": " + taskseconds;
        taskTime.setText(time);

        return convertView;
    }
}
