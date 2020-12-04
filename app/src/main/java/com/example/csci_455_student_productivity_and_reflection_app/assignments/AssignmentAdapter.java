package com.example.csci_455_student_productivity_and_reflection_app.assignments;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.csci_455_student_productivity_and_reflection_app.R;

import java.util.List;

public class AssignmentAdapter extends ArrayAdapter<Assignment> {
    public AssignmentAdapter(Context context, List<Assignment> object){
        super(context, 0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.task_items, parent, false);
        }

      //  TextView titleTextView = convertView.findViewById(R.id.ta);
        TextView currentGradeTextView = convertView.findViewById(R.id.course_average);

        Assignment assignment = getItem(position);

//        titleTextView.setText(assignment.getTitle());
        //currentGradeTextView.setText(Course.getCurrentAverage() + "");


        // Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_left);
        //convertView.startAnimation(animation);
        return convertView;
    }
}