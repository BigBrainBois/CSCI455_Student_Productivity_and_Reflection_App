package com.example.csci_455_student_productivity_and_reflection_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NoteInfo extends Activity {
    private TextView noteTitle, noteSubtitle, noteDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_info);

        noteTitle = findViewById(R.id.noteTitle);
        noteSubtitle = findViewById(R.id.noteSubtitle);
        noteDesc = findViewById(R.id.noteDescription);

    }

}
