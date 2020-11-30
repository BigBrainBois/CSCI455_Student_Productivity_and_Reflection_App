package com.example.csci_455_student_productivity_and_reflection_app.tasks;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.example.csci_455_student_productivity_and_reflection_app.TasksFragment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

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

        return convertView;
    }
}
