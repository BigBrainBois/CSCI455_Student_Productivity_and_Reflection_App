package com.example.csci_455_student_productivity_and_reflection_app.tasks;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.csci_455_student_productivity_and_reflection_app.R;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;


public class AddTask extends AppCompatActivity {

    private String date1;
    private Button dateButton;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Date Picker for task due date
        dateButton = findViewById(R.id.date_picker);
        configureDateButton();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void configureDateButton(){
        final DatePickerDialog dateDialogue = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK);
        dateDialogue.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date1 = String.format("%d/%d/%d", month+1, dayOfMonth, year);
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
}

