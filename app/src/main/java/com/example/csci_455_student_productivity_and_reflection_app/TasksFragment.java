package com.example.csci_455_student_productivity_and_reflection_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.csci_455_student_productivity_and_reflection_app.tasks.Task;
import com.example.csci_455_student_productivity_and_reflection_app.tasks.TaskAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;
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