package com.example.csci_455_student_productivity_and_reflection_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.example.csci_455_student_productivity_and_reflection_app.mood.TerribleActivity;
import com.example.csci_455_student_productivity_and_reflection_app.tasks.AddTask;
import com.example.csci_455_student_productivity_and_reflection_app.tasks.Task;
import com.example.csci_455_student_productivity_and_reflection_app.tasks.TaskAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.io.Console;

/*
READ ME

    *Quick Sidenote*
    The difference between a fragment and an activity is that an activity provides a screen that users interact with in order to do something,
    while fragments represent a behavior or a portion of UI in an activity.


    The current goal of the TaskFragment is to have the Recycler View display the task items ( see task_items xml for item format. ). Firestore
    has a few options/methods of their own that can be used to implement this, but currently this class uses Firestore Recycler Options (Thinking of
    switching to using a query snapshot instead ). Firestore Recycler Options helps to configure an adapter ( initialized in TaskAdapter ),
     to be set within the tasks list and hold the data fetched from the query. OnStart will begin listening for data, while onStop will remove
     the listener and all data in the adapter.

    Important Links : https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView


 */

public class TasksFragment extends Fragment{

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private TaskAdapter myAdapter;
    private FloatingActionButton newTask;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        fetchTasks(view);

        return  view;
    }

    private void fetchTasks(View view){

        Query query = db.collection("users").document("coolguy").collection("task").orderBy("date", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Task> options = new FirestoreRecyclerOptions.Builder<Task>().setQuery(query, Task.class).build();

        myAdapter = new TaskAdapter(options);

        recyclerView = view.findViewById(R.id.tasksList);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}
    /*
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView myTasksList;
    private FirestoreRecyclerAdapter adapter;
    private FloatingActionButton addTask;

    public TasksFragment(){
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                                           Bundle savedInstanceState) {

        View tasksFragmentView = inflater.inflate(R.layout.fragment_tasks, container, false);
        myTasksList = tasksFragmentView.findViewById(R.id.tasksList);
        myTasksList.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseFirestore = FirebaseFirestore.getInstance();

        // Start the addTask class by clicking the floating action button
        addTask = tasksFragmentView.findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTask.class);
                startActivity(intent);
            }
        });

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
                tasksViewHolder.task_title.setText(task.getTitle());
                String date = task.getDate() + "";
                tasksViewHolder.task_date.setText(date);
                tasksViewHolder.task_description.setText("");

                myTasksList.setHasFixedSize(true);
                myTasksList.setAdapter(adapter);
                adapter.startListening();

            }


        };
        myTasksList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

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