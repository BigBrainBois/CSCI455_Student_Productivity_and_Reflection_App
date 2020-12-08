package com.example.csci_455_student_productivity_and_reflection_app.assignments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csci_455_student_productivity_and_reflection_app.Dashboard;
import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AddAssignment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String date1, setGrade, setColor;

    private double setWeight;

    private TextView assignmentTitle, courseTitle;
    private Button dateButton, addAssignment;
    FirebaseFirestore db;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        // initializing Spinner options, see res /values/strings.xml for all initialized string arrays. [ "color" , " percentGrade ", " currentGrade " ]

        Spinner weightList = findViewById(R.id.weightSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.percentGrade, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        weightList.setAdapter(adapter);
        weightList.setOnItemSelectedListener(this);
        //casting selected value to desired variable
        setWeight = (double) weightList.getSelectedItem();


        Spinner colorList = findViewById(R.id.colorSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.color, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        colorList.setAdapter(adapter1);
        colorList.setOnItemSelectedListener(this);
        //casting selected value to desired variable
        setColor = (String) colorList.getSelectedItem();

        Spinner currentGradeList = findViewById(R.id.gradeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.currentGrade, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        currentGradeList.setAdapter(adapter2);
        currentGradeList.setOnItemSelectedListener(this);
        //casting selected value to desired variable
        setGrade = (String) currentGradeList.getSelectedItem();


        // initializing edit texts
        assignmentTitle = findViewById(R.id.assignmentTitle);
        courseTitle = findViewById(R.id.assignmentCourseTitle);

        // initializing add assignment button
        addAssignment = findViewById(R.id.addAssignment);

        //initializing firestore
        db = FirebaseFirestore.getInstance();

        // Date Picker for task due date
        dateButton = findViewById(R.id.date_picker);
        configureDateButton();

        //add assignment button event for when user clicks on the button.
        addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create input
                String mTitle = assignmentTitle.getText().toString().trim();
                String mCourseTitle = courseTitle.getText().toString().trim();


                //function call to upload input
                uploadData(mTitle, mCourseTitle, date1, setWeight, setColor, setGrade);
            }
        });
    }





    // handler for date selection
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

    //handler for uploading data using hash map.
    private void uploadData(String title, String course, String date, double weight, String color, String grade) {

        Map<String, Object> doc = new HashMap<>();
        doc.put("title", title);
        doc.put("course", course);
        doc.put("weight", weight);
        doc.put("color", color);
        doc.put("due date", date);
        doc.put("current grade", grade);

        //get the ID of the user that's logged in for query.
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uID = firebaseUser.getUid();

        //add hash map as location for query.
        db.collection("users").document(uID).collection("course").document().set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        //called when data is added successfully
                        Toast.makeText(AddAssignment.this, "Saved Successfully. ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddAssignment.this, Dashboard.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //if any errors occur while uploading
                        //...
                        //get and show error message
                        Toast.makeText(AddAssignment.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


