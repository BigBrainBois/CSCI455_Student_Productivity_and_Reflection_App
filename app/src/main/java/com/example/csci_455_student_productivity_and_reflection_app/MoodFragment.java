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
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.csci_455_student_productivity_and_reflection_app.mood.GoodActivity;
import com.example.csci_455_student_productivity_and_reflection_app.mood.OkayActivity;
import com.example.csci_455_student_productivity_and_reflection_app.mood.SadActivity;
import com.example.csci_455_student_productivity_and_reflection_app.mood.TerribleActivity;
import com.example.csci_455_student_productivity_and_reflection_app.mood.GreatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MoodFragment extends Fragment {


    //Name of the private variables that represents the facial expressions
    //and the animation when opening and closing the mood button
    private FloatingActionButton mood, terrible, sad, okay, good, great;
    private Animation fabOpen, fabClose, fabClockwise, fabCounterClockwise;

    //A boolean variable that helps initialize when the mood button is open or not
    private boolean isOpen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        super.onCreate(savedInstanceState);


        //Initializing the mood and animation
        // variables to its correct button

        mood = view.findViewById(R.id.mood_button);
        terrible = view.findViewById(R.id.terrible_button);
        sad = view.findViewById(R.id.sad_button);
        okay = view.findViewById(R.id.okay_button);
        good = view.findViewById(R.id.good_button);
        great = view.findViewById(R.id.great_button);
    //    CalendarView calendarView = view.findViewById(R.id.CalendarView);

        fabOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        fabClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_clockwise);
        fabCounterClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_counterclockwise);

        //Initializing the opposite if the mood button is not open
        isOpen = false;

        /**Nawshin's continuation for mood fragment code */

          List<EventDay> events = new ArrayList<>();
          Calendar calendar = Calendar.getInstance();
          events.add(new EventDay(calendar, R.drawable.ic_great_icon, Color.parseColor("#FF6666")));
          CalendarView calendarView = (CalendarView) view.findViewById(R.id.CalendarView);
          calendarView.setEvents(events);
          calendarView.setOnDayClickListener(new OnDayClickListener() {
              @Override
              public void onDayClick(EventDay eventDay) {
                  Calendar clickedDayCalendar = eventDay.getCalendar();
              }
          });


     //   TextView Display = view.findViewById(R.id.dateTextView);
     //   Bundle bn = getActivity().getIntent().getExtras();
      //  String date = bn.getString("abc");
      //  Display.setText(String.valueOf(date));


        // When the user clicks the mood button, each animation for
        //the facial expression will have an animation along with the button itself.
        //Otherwise, there will be no animation (minus the mood button)

        mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen){
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
        terrible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                terribleActivity();
                //Toast.makeText(getActivity(), "You clicked on terrible", Toast.LENGTH_SHORT).show();
            }
            private void terribleActivity(){
                Intent intent = new Intent(getActivity(), TerribleActivity.class);
                startActivity(intent);
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sadActivity();
                //Toast.makeText(getActivity(), "You clicked on sad", Toast.LENGTH_SHORT).show();
            }
            private void sadActivity(){
                Intent intent = new Intent(getActivity(), SadActivity.class);
                startActivity(intent);
            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                okayActivity();
                //Toast.makeText(getActivity(), "You clicked on okay", Toast.LENGTH_SHORT).show();
            }
            private void okayActivity(){
                Intent intent = new Intent(getActivity(), OkayActivity.class);
                startActivity(intent);
            }
        });

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodActivity();
                //Toast.makeText(getActivity(), "You clicked on good", Toast.LENGTH_SHORT).show();
            }
            private void goodActivity(){
                Intent intent = new Intent(getActivity(), GoodActivity.class);
                startActivity(intent);
            }
        });

        great.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                greatActivity();
            }

            private void greatActivity() {
                Intent intent = new Intent(getActivity(), GreatActivity.class);
                startActivity(intent);
            }
        });

        return view; // display mood fragment
    }


}
