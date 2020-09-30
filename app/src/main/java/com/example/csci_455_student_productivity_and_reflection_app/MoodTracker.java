package com.example.csci_455_student_productivity_and_reflection_app;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MoodTracker extends AppCompatActivity{

    //Naming the variables
    FloatingActionButton mood, terrible, sad, okay, good, great;
    Animation fabOpen, fabClose, fabClockwise, fabCounterClockwise;

    boolean isOpen = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mood); //The page to access the layout

        //Initializing the variables to its correct button
        mood = findViewById(R.id.mood_button);
        terrible = findViewById(R.id.terrible_button);
        sad = findViewById(R.id.sad_button);
        okay = findViewById(R.id.okay_button);
        good = findViewById(R.id.good_button);
        great = findViewById(R.id.great_button);

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        fabCounterClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_counterclockwise);

        //Tapping the mood button function
        mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MoodTracker.this, "You clicked on terrible", Toast.LENGTH_SHORT).show();

                if(!isOpen) {
                    terrible.startAnimation(fabClose);
                    sad.startAnimation(fabClose);
                    okay.startAnimation(fabClose);
                    good.startAnimation(fabClose);
                    great.startAnimation(fabClose);
                    mood.startAnimation(fabClockwise);

                    terrible.setClickable(false);
                    sad.setClickable(false);
                    okay.setClickable(false);
                    good.setClickable(false);
                    great.setClickable(false);

                    isOpen = false;
                }
                else{
                    terrible.startAnimation(fabOpen);
                    sad.startAnimation(fabOpen);
                    okay.startAnimation(fabOpen);
                    good.startAnimation(fabOpen);
                    great.startAnimation(fabOpen);
                    mood.startAnimation(fabCounterClockwise);

                    terrible.setClickable(true);
                    sad.setClickable(true);
                    okay.setClickable(true);
                    good.setClickable(true);
                    great.setClickable(true);

                    isOpen = true;
                }

            }
        });

        //Clicking each mood
        terrible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MoodTracker.this, "You clicked on terrible", Toast.LENGTH_SHORT).show();
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MoodTracker.this, "You clicked on sad", Toast.LENGTH_SHORT).show();
            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MoodTracker.this, "You clicked on okay", Toast.LENGTH_SHORT).show();
            }
        });

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MoodTracker.this, "You clicked on good", Toast.LENGTH_SHORT).show();
            }
        });

        great.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MoodTracker.this, "You clicked on great", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
