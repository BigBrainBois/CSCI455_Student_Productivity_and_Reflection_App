package com.example.csci_455_student_productivity_and_reflection_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.example.csci_455_student_productivity_and_reflection_app.mood.TerribleActivity;
import com.example.csci_455_student_productivity_and_reflection_app.tasks.AddTask;
import com.example.csci_455_student_productivity_and_reflection_app.tasks.Task;
import com.example.csci_455_student_productivity_and_reflection_app.tasks.TaskAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;



/*
* IMPORTANT CHANGES
*   -RecyclerView turned into ListView -- all the commented out lines were from using Recyclerview are getting errors and crashing the app.
*   -Need to rewrite code to cater to ListView instead of RecyclerView
* */

public class TasksFragment extends Fragment {

    private ListView mTaskListView;

    private FirebaseFirestore db;

    //Adapter
    private TaskAdapter mTaskAdapter;
    private ArrayList<Task> mTaskList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View tasksFragmentView = inflater.inflate(R.layout.fragment_tasks, container, false);


        mTaskListView = tasksFragmentView.findViewById(R.id.tasksList);

        //get database
            db = FirebaseFirestore.getInstance();
        //Set up the ArrayList
            mTaskList = new ArrayList<Task>();
        //Set up the adapter
            mTaskAdapter = new TaskAdapter(getContext(), mTaskList);

            mTaskListView.setAdapter(mTaskAdapter);

            db.collection("users").document("coolguy").collection("task")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                    List<Task> mTaskList = new ArrayList<>();
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot snapshot : task.getResult()) {
                            Task t = snapshot.toObject(Task.class);
                            mTaskList.add(t);
                        }

                        mTaskAdapter.addAll(mTaskList);
                        mTaskAdapter.notifyDataSetChanged();
                        mTaskListView.setAdapter(mTaskAdapter);
                    }
                }
            });
            return tasksFragmentView;
    }

}