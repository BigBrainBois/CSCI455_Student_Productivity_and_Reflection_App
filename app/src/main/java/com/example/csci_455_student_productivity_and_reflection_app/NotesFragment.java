package com.example.csci_455_student_productivity_and_reflection_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.example.csci_455_student_productivity_and_reflection_app.notes.NotesCreate;

public class NotesFragment extends Fragment {

    private ListView NotesListView;

    public static final int REQUEST_CODE_ADD_NOTE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notes, container, false);

        // button to another activity for creating a note
        ImageView imageAddNoteMain = v.findViewById(R.id.addNote);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), NotesCreate.class),
                        REQUEST_CODE_ADD_NOTE);
            }
        });

        // setting the list for showing notes
        NotesListView = v.findViewById(R.id.notesListView);


        // return view
        return v;
    }
}
