package com.example.csci_455_student_productivity_and_reflection_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TasksFragment extends Fragment {

    TextView title, subtitle, endpage;
    DatabaseReference reference;
    RecyclerView tasksList;
    ArrayList<MyTasks> list;
    TasksAdapter tasksAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasks, container, false);
        title = v.findViewById(R.id.title);
        subtitle = v.findViewById(R.id.moment_of_zen);
        endpage = v.findViewById(R.id.endpage);
        tasksList = v.findViewById(R.id.tasksList);
        tasksList.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<MyTasks>();

        // get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("MyTasks");
        tasksAdapter = new TasksAdapter(getActivity(), list);
        tasksList.setAdapter(tasksAdapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // set code to retrieve data
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    MyTasks t = dataSnapshot1.getValue(MyTasks.class);
                    list.add(t);
                }
                tasksAdapter = new TasksAdapter(getActivity(), list);
                tasksList.setAdapter(tasksAdapter);
                tasksAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // set code if no tasks shown
                Toast.makeText(getContext(), "No Tasks", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
