package com.example.csci_455_student_productivity_and_reflection_app.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class TasksFragment extends Fragment {


    private FirebaseFirestore firebaseFirestore;
    private RecyclerView myTasksList;
    private FirestoreRecyclerAdapter adapter;

    public TasksFragment(){

    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                                           Bundle savedInstanceState) {

        View tasksFragmentView = inflater.inflate(R.layout.fragment_tasks, container, false);

        myTasksList = tasksFragmentView.findViewById(R.id.tasksList);
        myTasksList.setLayoutManager(new LinearLayoutManager(getContext()));

        firebaseFirestore = FirebaseFirestore.getInstance();

        return tasksFragmentView;
    }

    @Override
    public void onStart(){
        super.onStart();

        Query query = firebaseFirestore.collection("users").document("coolguy").collection("task");
        FirestoreRecyclerOptions<Task> options = new FirestoreRecyclerOptions.Builder<Task>().setQuery(query, Task.class).build();
        adapter = new FirestoreRecyclerAdapter<Task, TasksViewHolder>(options) {

            @NonNull
            @Override
            public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_items, parent, false);
                return new TasksViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(TasksViewHolder tasksViewHolder, int i, Task task) {
                tasksViewHolder.task_title.setText(task.getJournalID());
                String date = task.getStartDate() + "";
                tasksViewHolder.task_date.setText(date);
                tasksViewHolder.task_description.setText("");
            }
        };

        myTasksList.setHasFixedSize(true);
        myTasksList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        myTasksList.setAdapter(adapter);
        adapter.startListening();

    }

    private class TasksViewHolder extends RecyclerView.ViewHolder{

        private TextView task_title;
        private TextView task_description;
        private TextView task_date;

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);

            task_title = itemView.findViewById(R.id.task_title);
            task_description = itemView.findViewById(R.id.task_description);
            task_date = itemView.findViewById(R.id.task_date);


        }

    }

    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}