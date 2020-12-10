package com.example.csci_455_student_productivity_and_reflection_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.csci_455_student_productivity_and_reflection_app.assignments.AddAssignment;
import com.example.csci_455_student_productivity_and_reflection_app.assignments.Assignment;
import com.example.csci_455_student_productivity_and_reflection_app.assignments.AssignmentAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private ListView mAssignmentListView;

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    //Adapter
    private AssignmentAdapter mAssignmentAdapter;
    private ArrayList<Assignment> mAssignmentist;

    private FloatingActionButton addAssignment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View assignmentsFragmentView = inflater.inflate(R.layout.fragment_tasks, container, false);

        addAssignment = assignmentsFragmentView.findViewById(R.id.addTask);

        mAssignmentListView = assignmentsFragmentView.findViewById(R.id.tasksList);

        //get database
            db = FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();
        //Set up the ArrayList
        mAssignmentist = new ArrayList<Assignment>();
        //Set up the adapter
        mAssignmentAdapter= new AssignmentAdapter(getContext(), mAssignmentist);

            mAssignmentListView.setAdapter(mAssignmentAdapter);

        FirebaseUser user = auth.getInstance().getCurrentUser();

            db.collection("users").document(user.getUid()).collection("assignments").orderBy("weight", Query.Direction.ASCENDING)
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                    List<Assignment> mAssignmentList = new ArrayList<>();
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot snapshot : task.getResult()) {
                            Assignment a = snapshot.toObject(Assignment.class);
                            mAssignmentList.add(a);
                        }

                        mAssignmentAdapter.addAll(mAssignmentList);
                        mAssignmentAdapter.notifyDataSetChanged();
                        mAssignmentListView.setAdapter(mAssignmentAdapter);
                    }
                }
            });

            addAssignment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), AddAssignment.class));
                }
            });

            return assignmentsFragmentView;
    }

}