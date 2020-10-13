package com.example.csci_455_student_productivity_and_reflection_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Tasks;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyTasks> tasks;

    public TasksAdapter(Context c, ArrayList<MyTasks> t){
        context = c;
        tasks = t;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.task_items, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.taskTitle.setText(tasks.get(position).getTaskTitle());
        holder.taskDesc.setText(tasks.get(position).getTaskDesc());
        holder.taskDate.setText(tasks.get(position).getTaskDate());
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

       TextView taskTitle, taskDesc, taskDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = (TextView) itemView.findViewById(R.id.task_title);
            taskDesc = (TextView) itemView.findViewById(R.id.task_description);
            taskDate = (TextView) itemView.findViewById(R.id.task_date);
        }
    }
}
