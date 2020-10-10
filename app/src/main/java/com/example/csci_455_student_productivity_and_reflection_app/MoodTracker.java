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

    boolean isOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mood); //The page to access the layout

        //Initializing the variables to its correct button
        mood = (FloatingActionButton) findViewById(R.id.mood_button);
        terrible = (FloatingActionButton) findViewById(R.id.terrible_button);
        sad = (FloatingActionButton) findViewById(R.id.sad_button);
        okay = (FloatingActionButton) findViewById(R.id.okay_button);
        good = (FloatingActionButton) findViewById(R.id.good_button);
        great = (FloatingActionButton) findViewById(R.id.great_button);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        fabClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise);
        fabCounterClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_counterclockwise);

        //Clicking each mood
        mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
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
    //Tapping the mood button function
    private void animateFab(){
        if(isOpen){
            mood.startAnimation(fabClockwise);
            terrible.startAnimation(fabClose);
            sad.startAnimation(fabClose);
            okay.startAnimation(fabClose);
            good.startAnimation(fabClose);
            great.startAnimation(fabClose);
            terrible.setClickable(false);
            sad.setClickable(false);
            okay.setClickable(false);
            good.setClickable(false);
            great.setClickable(false);
            isOpen = false;
        } else{
            mood.startAnimation(fabCounterClockwise);
            terrible.startAnimation(fabOpen);
            sad.startAnimation(fabOpen);
            okay.startAnimation(fabOpen);
            good.startAnimation(fabOpen);
            great.startAnimation(fabOpen);
            terrible.setClickable(true);
            sad.setClickable(true);
            okay.setClickable(true);
            good.setClickable(true);
            great.setClickable(true);
            isOpen = true;
        }

    }
}
