package com.example.csci_455_student_productivity_and_reflection_app.notes;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.csci_455_student_productivity_and_reflection_app.R;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {

   public NoteAdapter(Context context, List<Note> object){
       super(context, 0, object);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
        convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.items_notes, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.note_title);
        TextView subtitleTextView = convertView.findViewById(R.id.note_subtitle);


        Note Note = getItem(position);

        titleTextView.setText(Note.getTitle());
        subtitleTextView.setText(Note.getSubtitle());



        // Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_left);
        //convertView.startAnimation(animation);
        return convertView;
        }
}

