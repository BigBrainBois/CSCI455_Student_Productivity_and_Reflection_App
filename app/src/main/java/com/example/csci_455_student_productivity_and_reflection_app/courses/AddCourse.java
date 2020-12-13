package com.example.csci_455_student_productivity_and_reflection_app.courses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csci_455_student_productivity_and_reflection_app.Dashboard;
import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddCourse extends AppCompatActivity {

        private EditText courseTitle;
        private EditText courseGrade;
        private Button createCourse;
        FirebaseFirestore db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_course);

            courseTitle = findViewById(R.id.courseTitle);
            courseGrade = findViewById(R.id.courseGrade);
            createCourse = findViewById(R.id.addCourse);

            db = FirebaseFirestore.getInstance();

            createCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //input data
                    String title = courseTitle.getText().toString().trim();

                    //function call to upload data
                    uploadData(title);
                }
            });

            // ImageView imageBack = findViewById(R.id.backButton);
            //  imageBack.setOnClickListener(new View.OnClickListener() {
            //   @Override
            //  public void onClick(View view) {
            //    onBackPressed();
            //    }

            //   });
            //   }
        }

        private void uploadData(String title) {

            Map<String, Object> doc = new HashMap<>();
            doc.put("title", title);

            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String uID = firebaseUser.getUid();

            String document = courseTitle.getText().toString();

            //add this data
            db.collection("users").document(uID).collection("courses").document(document).set(doc)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            /* called when data is added successfully */

                          Toast.makeText(AddCourse.this, "Saved Successfully. ", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(AddCourse.this, Dashboard.class);
                          startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //if any errors occur while uploading

                            //get and show error message
                          Toast.makeText(AddCourse.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

        }
    }

