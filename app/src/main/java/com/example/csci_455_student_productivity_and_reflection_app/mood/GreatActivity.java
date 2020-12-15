package com.example.csci_455_student_productivity_and_reflection_app.mood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.csci_455_student_productivity_and_reflection_app.Dashboard;
import com.example.csci_455_student_productivity_and_reflection_app.MoodFragment;
import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GreatActivity extends AppCompatActivity {

    //State the variables for the date button
    private TextView dateView;
    private DatePickerDialog.OnDateSetListener listener;
    public CalendarView calendarView;
    private EditText text;
    private FirebaseFirestore db;
    private String day = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
    private Button color;
    public MoodFragment moodFrag = new MoodFragment();
    List<EventDay> currEvent = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_great);

        // access events from another class ("MoodFragment")
        View inflatedView = getLayoutInflater().inflate(R.layout.fragment_mood, null);

        //Initialize each date variable to its correspondent function
        dateView = findViewById(R.id.dateView);
        text = findViewById(R.id.addTitle);
        color = (Button) findViewById(R.id.great_button);
//        List<EventDay> events = new ArrayList<>();
        currEvent = moodFrag.events;
        Calendar calendar = Calendar.getInstance();
        calendarView = inflatedView.findViewById(R.id.CalendarView);


        //Initialize the save button that will make the user click the button
        //to save the info in the journal

        Button saveButton = findViewById(R.id.greatSaveButton);
        db = FirebaseFirestore.getInstance();

        //When the date button is click, it pops up a calendar
        //that calendar has a specific theme and shows the year, month, and day.
        //The listener helps with the display when the user clicks on the date.

        dateView.setText(day);
        saveButton.setOnClickListener(view -> {
            String greatDate = day;
            String greatText = text.getText().toString().trim();
            String greatColor = String.valueOf(currEvent.add(new EventDay(calendar, R.drawable.ic_great_icon, Color.parseColor("#FF6666"))));

            //update database
            uploadData(greatDate, greatText, greatColor);


            //add mood icon to today's date
            currEvent.add(new EventDay(calendar, R.drawable.ic_great_icon, Color.parseColor("#FF6666")));
            calendarView.setEvents(currEvent);
        });

    }

    // upload to firebase
    private void uploadData(String greatDate, String greatText, String greatColor) {
        Map<String, Object> doc = new HashMap<>();
        doc.put("Date", greatDate);
        doc.put("Text", greatText);
        doc.put("Mood", greatColor);
        // doc.put("Color", greatColor);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uID = firebaseUser.getUid();


        db.collection("users").document(uID).collection("journal").document(greatDate).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //called when data is added successfully

                        Toast.makeText(GreatActivity.this, "Saved Successfully. ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GreatActivity.this, Dashboard.class);
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
}