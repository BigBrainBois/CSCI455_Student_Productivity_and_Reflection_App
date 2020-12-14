package com.example.csci_455_student_productivity_and_reflection_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

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

public class CourseInfo extends Activity {

    private TextView courseTitle, courseGrade;
    private ListView mAssignmentListView;
    private FloatingActionButton addCourse;

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    //Adapter
    private AssignmentAdapter mAssignmentAdapter;
    private ArrayList<Assignment> mAssignmentist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        courseTitle = findViewById(R.id.title_note);
        courseGrade = findViewById(R.id.subtitle);
        addCourse = findViewById(R.id.add_course);

        mAssignmentListView = findViewById(R.id.courselist);

        Intent intent = getIntent();
        String title = intent.getStringExtra("course title");
        Double grade = intent.getDoubleExtra("course grade", 0);

        courseTitle.setText(title);
        courseGrade.setText(grade + "");

        //get database
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        //Set up the ArrayList
        mAssignmentist = new ArrayList<Assignment>();
        //Set up the adapter
        mAssignmentAdapter= new AssignmentAdapter(getApplicationContext(), mAssignmentist);

        mAssignmentListView.setAdapter(mAssignmentAdapter);

        FirebaseUser user = auth.getInstance().getCurrentUser();

        db.collection("users").document(user.getUid()).collection("courses").document(title).collection("assignments").orderBy("weight", Query.Direction.ASCENDING)
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


        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseInfo.this, AddAssignment.class);
                startActivity(intent);
            }
        });


    }
}
