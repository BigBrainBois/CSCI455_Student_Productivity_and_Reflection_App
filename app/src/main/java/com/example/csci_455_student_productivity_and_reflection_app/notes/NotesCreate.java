package com.example.csci_455_student_productivity_and_reflection_app.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NotesCreate extends AppCompatActivity {

    private EditText noteTitle, noteSubtitle, noteDescription;
    private ImageView createNote;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        noteTitle = findViewById(R.id.noteTitle);
        noteSubtitle = findViewById(R.id.noteSubtitle);
        noteDescription = findViewById(R.id.noteContents);

        createNote = findViewById(R.id.doneButton);

        db = FirebaseFirestore.getInstance();

        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input data
                String title = noteTitle.getText().toString().trim();
                String subtitle = noteSubtitle.getText().toString().trim();
                String description = noteDescription.getText().toString().trim();

                //function call to upload data
                uploadData(title, subtitle, description);
            }
        });

        ImageView imageBack = findViewById(R.id.backButton);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }

        });
    }

    private void uploadData(String title, String subtitle, String description) {

        Map<String, Object> doc = new HashMap<>();
        doc.put("title", title);
        doc.put("subtitle", subtitle);
        doc.put("description", description);

        //add this data
        db.collection("users").document("coolguy").collection("notes").document().set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //called when data is added successfully

                        Toast.makeText(NotesCreate.this, "Saved Successfully. ", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //if any errors occur while uploading

                        //get and show error message
                        Toast.makeText(NotesCreate.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }
}