package com.example.csci_455_student_productivity_and_reflection_app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.csci_455_student_productivity_and_reflection_app.mood.GoodActivity;
import com.example.csci_455_student_productivity_and_reflection_app.mood.OkayActivity;
import com.example.csci_455_student_productivity_and_reflection_app.mood.SadActivity;
import com.example.csci_455_student_productivity_and_reflection_app.mood.TerribleActivity;
import com.example.csci_455_student_productivity_and_reflection_app.mood.GreatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class MoodFragment extends Fragment {


    //Name of the private variables that represents the facial expressions
    //and the animation when opening and closing the mood button
    private FloatingActionButton mood, terrible, sad, okay, good, great;
    private Animation fabOpen, fabClose, fabClockwise, fabCounterClockwise;
    public List<EventDay> events = new ArrayList<>();
    public Calendar calendar = Calendar.getInstance();
    //A boolean variable that helps initialize when the mood button is open or not
    private boolean isOpen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        super.onCreate(savedInstanceState);

        // Initializing the mood and animation
        //    variables to its correct button
        mood = view.findViewById(R.id.mood_button);
        terrible = view.findViewById(R.id.terrible_button);
        sad = view.findViewById(R.id.sad_button);
        okay = view.findViewById(R.id.okay_button);
        good = view.findViewById(R.id.good_button);
        great = view.findViewById(R.id.great_button);

        CalendarView calendarView = (CalendarView) view.findViewById(R.id.CalendarView);

        fabOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        fabClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_clockwise);
        fabCounterClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_counterclockwise);

        //Initializing the opposite if the mood button is not open
        isOpen = false;


        // When the user clicks the mood button, each animation for
        //the facial expression will have an animation along with the button itself.
        //Otherwise, there will be no animation (minus the mood button)

        mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    mood.startAnimation(fabClockwise);
                    terrible.startAnimation(fabClose);
                    sad.startAnimation(fabClose);
                    okay.startAnimation(fabClose);
                    good.startAnimation(fabClose);
                    great.startAnimation(fabClose);
                    isOpen = false;
                } else {
                    mood.startAnimation(fabCounterClockwise);
                    terrible.startAnimation(fabOpen);
                    sad.startAnimation(fabOpen);
                    okay.startAnimation(fabOpen);
                    good.startAnimation(fabOpen);
                    great.startAnimation(fabOpen);
                    isOpen = true;
                }
            }
        });


        // When a user clicks each of the facial expression
        //it opens to the journal page. Will remove the comment out sections
        //after the functionalities of the MoodFragment page is fully complete.
        // Need to add functionality, aka get user input to display on moodfragment

        terrible.setOnClickListener(viewTerr -> {
            Intent intent = new Intent(getActivity(), TerribleActivity.class);
            startActivity(intent);
        });

        sad.setOnClickListener(viewSad -> {
            Intent intent = new Intent(getActivity(), SadActivity.class);
            startActivity(intent);
        });

        okay.setOnClickListener(viewOkay -> {
            Intent intent = new Intent(getActivity(), OkayActivity.class);
            startActivity(intent);
        });

        good.setOnClickListener(viewGood -> {
            Intent intent = new Intent(getActivity(), GoodActivity.class);
            startActivity(intent);
        });

        great.setOnClickListener(viewGreat -> {
            Intent intent = new Intent(getActivity(), GreatActivity.class);
            startActivity(intent);
        });

        return view; // display mood fragment
    }
}
