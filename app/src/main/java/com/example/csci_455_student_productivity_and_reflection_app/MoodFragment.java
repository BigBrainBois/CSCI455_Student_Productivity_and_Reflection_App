package com.example.csci_455_student_productivity_and_reflection_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MoodFragment extends Fragment {

    //Naming the variables
    FloatingActionButton mood, terrible, sad, okay, good, great;
    Animation fabOpen, fabClose, fabClockwise, fabCounterClockwise;

    boolean isOpen = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        super.onCreate(savedInstanceState);

        //Initializing the variables to its correct button

        FloatingActionButton mood = view.findViewById(R.id.mood_button);
        FloatingActionButton terrible = view.findViewById(R.id.terrible_button);
        FloatingActionButton sad = view.findViewById(R.id.sad_button);
        FloatingActionButton okay = view.findViewById(R.id.okay_button);
        FloatingActionButton good = view.findViewById(R.id.good_button);
        FloatingActionButton great = view.findViewById(R.id.great_button);

        fabOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        fabClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_clockwise);
        fabCounterClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_counterclockwise);

        // Functionality of each button/mood
        mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
        terrible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You clicked on terrible", Toast.LENGTH_SHORT).show();
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You clicked on sad", Toast.LENGTH_SHORT).show();
            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You clicked on okay", Toast.LENGTH_SHORT).show();
            }
        });

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You clicked on good", Toast.LENGTH_SHORT).show();
            }
        });

        great.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You clicked on great", Toast.LENGTH_SHORT).show();
            }
        });

        return view; // inflate view
    }

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
