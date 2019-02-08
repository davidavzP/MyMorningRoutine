package com.example.mymorningroutine;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private int mResource;

    public CustomListViewAdapter(Context context, int resource, List<Task> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource =resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String taskname = getItem(position).getTaskName();
        String taskhours = getItem(position).getTaskHours();
        String taskminutes = getItem(position).getTaskMinutes();
        String taskseconds = getItem(position).getTaskSeconds();

        Task task = new Task(taskname, taskhours, taskminutes, taskseconds);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView taskName = convertView.findViewById(R.id.userTaskname);

        taskName.setText(taskname);
        return convertView;
    }
}