package com.example.csci_455_student_productivity_and_reflection_app;
import android.app.Activity;
import android.content.Intent;
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

        Intent intent = getIntent();
        String title = intent.getStringExtra("note title");
        String subtitle = intent.getStringExtra("note subtitle");
        String description = intent.getStringExtra("note description");

        noteTitle.setText(title);
        noteSubtitle.setText(subtitle);
        noteDesc.setText(description);
    }

}
