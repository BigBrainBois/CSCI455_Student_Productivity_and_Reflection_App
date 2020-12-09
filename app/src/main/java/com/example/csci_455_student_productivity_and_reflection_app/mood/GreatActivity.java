package com.example.csci_455_student_productivity_and_reflection_app.mood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csci_455_student_productivity_and_reflection_app.Dashboard;
import com.example.csci_455_student_productivity_and_reflection_app.MoodFragment;
import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.example.csci_455_student_productivity_and_reflection_app.notes.NotesCreate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class GreatActivity extends AppCompatActivity {

    //State the variables for the date button

    Button dateView;
    DatePickerDialog.OnDateSetListener listener;
    EditText text;
    FirebaseFirestore db;
    Button color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_great);

        //Initialize each date variable to its correspondent
        //function

        dateView = findViewById(R.id.dateView);
        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        text = findViewById(R.id.addTitle);
        color = (Button) findViewById(R.id.great_button);

        //Initialize the save button that will make the user click the button
        //to save the info in the journal

        Button saveButton = findViewById(R.id.greatSaveButton);

        db = FirebaseFirestore.getInstance();

        //When the date button is click, it pops up a calendar
        //that calendar has a specific theme and shows the year, month, and day.
        //The listener helps with the display when the user clicks on the date.

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(GreatActivity.this, AlertDialog.THEME_HOLO_DARK,
                        listener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                dateView.setText(date);


            }
        };

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String greatDate = dateView.getText().toString().trim();
                String greatText = text.getText().toString().trim();
                String greatColor = color.getText().toString().trim();

                uploadData(greatDate, greatText, greatColor);

            }
        });


      //  private void uploadData(){



        //    String greatDate = dateView.getText().toString();
          //  String greatText = text.getText().toString();
           // String greatColor = color.getText().toString();

            //if(!TextUtils.isEmpty(greatDate) && !TextUtils.isEmpty(greatText)){

            //}else{
              //  Toast.makeText(GreatActivity.this, "Please type what you do on the text box and select a date", Toast.LENGTH_LONG).show();
            //}
        }
    private void uploadData(String greatDate, String greatText, String greatColor) {
        Map<String, Object> doc = new HashMap<>();
        doc.put("Date", greatDate);
        doc.put("Text", greatText);
        doc.put("Color", greatColor);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uID = firebaseUser.getUid();

        db.collection("users").document(uID).collection("journal").document(greatDate).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //called when data is added successfully

                        Toast.makeText(GreatActivity.this, "Saved Successfully. ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GreatActivity.this, MoodFragment.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //if any errors occur while uploading

                        //get and show error message
                        Toast.makeText(GreatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }



    /**Nawshin's continuation for mood fragment code */
     //   saveButton.setOnClickListener(new View.OnClickListener() {
       //     @Override
        //    public void onClick(View view) {
         //       String Data = dateView.getText().toString();
          //      Intent intent = new Intent(GreatActivity.this, MoodFragment.class);
           //     intent.putExtra("abc", Data);
            //    startActivity(intent);
             //   finish();
           // }
        //});


    }

//}