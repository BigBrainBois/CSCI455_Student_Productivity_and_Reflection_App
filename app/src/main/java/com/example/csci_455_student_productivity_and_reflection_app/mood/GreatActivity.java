package com.example.csci_455_student_productivity_and_reflection_app.mood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.csci_455_student_productivity_and_reflection_app.MoodFragment;
import com.example.csci_455_student_productivity_and_reflection_app.R;

import java.text.DateFormat;
import java.util.Calendar;

public class GreatActivity extends AppCompatActivity {

    //State the variables for the date button

    Button dateView;
    DatePickerDialog.OnDateSetListener listener;


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

        //Initialize the save button that will make the user click the button
        //to save the info in the journal

        Button saveButton = findViewById(R.id.SaveButton);

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

}