package com.example.csci_455_student_productivity_and_reflection_app.tasks;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csci_455_student_productivity_and_reflection_app.Dashboard;
import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.example.csci_455_student_productivity_and_reflection_app.notes.NotesCreate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AddTask extends AppCompatActivity {

    private String date1;
    private TextView taskTitle, taskDescription;
    private Button dateButton, addTask;
    FirebaseFirestore db;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskTitle = findViewById(R.id.addTitle);
        taskDescription = findViewById(R.id.addDescription);

        addTask = findViewById(R.id.submit);

        db = FirebaseFirestore.getInstance();

        // Date Picker for task due date
        dateButton = findViewById(R.id.date_picker);
        configureDateButton();

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input data
                String title = taskTitle.toString().trim();
                String description = taskDescription.toString().trim();


                //function call to upload data
                uploadData(title, description);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void configureDateButton() {
        final DatePickerDialog dateDialogue = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK);
        dateDialogue.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date1 = String.format("%d/%d/%d", month + 1, dayOfMonth, year);
                dateButton.setText(date1);
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialogue.show();
            }
        });
    }

    private void uploadData(String title, String description) {

        Map<String, Object> doc = new HashMap<>();
        doc.put("title", title);
        doc.put("description", description);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = firebaseUser.getUid();

        //add this data
        db.collection("users").document("coolkid").collection("course").document("english").collection("assignments").document().set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //called when data is added successfully

                        Toast.makeText(AddTask.this, "Saved Successfully. ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddTask.this, Dashboard.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //if any errors occur while uploading

                        //get and show error message
                        Toast.makeText(AddTask.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }


    }


