package com.example.csci_455_student_productivity_and_reflection_app.tasks;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.example.csci_455_student_productivity_and_reflection_app.TasksFragment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class TaskAdapter extends FirestoreRecyclerAdapter <Task, TaskAdapter.TaskHolder>{

    public TaskAdapter(@NonNull FirestoreRecyclerOptions<Task> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TaskHolder holder, int position, @NonNull Task model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewDescription.setText(model.getDescription());
        holder.textViewDate.setText(String.valueOf(model.getDate()));
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_items, viewGroup, false);

        return new TaskHolder(v);
    }

    class TaskHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewDate;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.task_title);
            textViewDescription = itemView.findViewById(R.id.task_description);
            textViewDate = itemView.findViewById(R.id.task_date);
        }
    }
}
