package com.example.csci_455_student_productivity_and_reflection_app.tasks;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.csci_455_student_productivity_and_reflection_app.R;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(Context context, List<Task> object){
        super(context, 0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.task_items, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.task_title);
        TextView descriptionTextView = convertView.findViewById(R.id.task_description);
        TextView dateTextView = convertView.findViewById(R.id.task_date);

        Task task = getItem(position);

        titleTextView.setText(task.getTitle());
        descriptionTextView.setText(task.getDescription());
        dateTextView.setText(task.getDate() + "");


       // Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_left);
        //convertView.startAnimation(animation);
        return convertView;
    }
}
