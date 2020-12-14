package com.example.csci_455_student_productivity_and_reflection_app.courses;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.csci_455_student_productivity_and_reflection_app.R;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course>{
        public CourseAdapter(Context context, List<Course> object){
            super(context, 0, object);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.items_course, parent, false);

            }

            TextView titleTextView = convertView.findViewById(R.id.title_note);
            TextView currentGradeTextView = convertView.findViewById(R.id.course_average);

            Course Course = getItem(position);

            titleTextView.setText(Course.getTitle());
            currentGradeTextView.setText(Course.getCurrentGrade() + "");

            // Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_left);
            //convertView.startAnimation(animation);
            return convertView;
        }
    }

