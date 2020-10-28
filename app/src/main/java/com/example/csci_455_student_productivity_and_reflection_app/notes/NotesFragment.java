package com.example.csci_455_student_productivity_and_reflection_app.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.csci_455_student_productivity_and_reflection_app.R;

public class NotesFragment extends Fragment {

    public static final int REQUEST_CODE_ADD_NOTE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notes, container, false);

        ImageView imageAddNoteMain = v.findViewById(R.id.addNote);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), NotesCreate.class),
                        REQUEST_CODE_ADD_NOTE);
            }
        });

        return v;
    }
}
