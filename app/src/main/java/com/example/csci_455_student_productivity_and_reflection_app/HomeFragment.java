package com.example.csci_455_student_productivity_and_reflection_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.csci_455_student_productivity_and_reflection_app.courses.Course;
import com.example.csci_455_student_productivity_and_reflection_app.courses.CourseAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

//https://stackoverflow.com/questions/49659549/how-to-show-a-firestore-collection-in-an-android-listview-using-an-adapter

public class HomeFragment extends Fragment {
    private ListView CourseListView;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    //Adapter
    private CourseAdapter CourseAdapter;
    private ArrayList<Course> CourseList;

    private TextView greeting;
    private TextView date;
    String day = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View courseFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        CourseListView = courseFragmentView.findViewById(R.id.courselist);

        greeting = courseFragmentView.findViewById(R.id.greeting);
        date = courseFragmentView.findViewById(R.id.date);
        mAuth = FirebaseAuth.getInstance();

        //get database
        db = FirebaseFirestore.getInstance();
        //Set up the ArrayList
        CourseList = new ArrayList<Course>();
        //Set up the adapter
        CourseAdapter = new CourseAdapter(getContext(), CourseList);

        CourseListView.setAdapter(CourseAdapter);

        FirebaseUser user = mAuth.getCurrentUser();
        String[] firstName = user.getDisplayName().split("\\s");
        greeting.setText(" Welcome back, \n" + firstName[0]);
        date.setText("Today is " + day);

        db.collection("users");


        db.collection("users").document(user.getUid()).collection("course").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                       Course c = document.toObject(Course.class);
                        CourseList.add(c);
                    }

                    CourseAdapter.addAll(CourseList);
                    CourseAdapter.notifyDataSetChanged();
                    CourseListView.setAdapter(CourseAdapter);
                    }
                }

        });

        return courseFragmentView;
    }


}
