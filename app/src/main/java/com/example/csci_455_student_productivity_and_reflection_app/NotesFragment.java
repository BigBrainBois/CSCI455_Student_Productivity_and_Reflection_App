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

import com.example.csci_455_student_productivity_and_reflection_app.notes.Note;
import com.example.csci_455_student_productivity_and_reflection_app.notes.NoteAdapter;
import com.example.csci_455_student_productivity_and_reflection_app.notes.NotesCreate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    private ListView notesListView;

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    //Adapter
    private NoteAdapter mNoteAdapter;
    private ArrayList<Note> mNoteList;

    public static final int REQUEST_CODE_ADD_NOTE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View notesFragmentView = inflater.inflate(R.layout.fragment_notes, container, false);

        notesListView = notesFragmentView.findViewById(R.id.notesListView);

        //get database
            db = FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();

        //Set up the ArrayList
        mNoteList = new ArrayList<Note>();
        //Set up the adapter
        mNoteAdapter = new NoteAdapter(getContext(), mNoteList);

            notesListView.setAdapter(mNoteAdapter);

        FirebaseUser user = auth.getInstance().getCurrentUser();

        db.collection("users").document(user.getUid()).collection("notes").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                        List<Note> mNoteList = new ArrayList<>();
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot snapshot : task.getResult()){
                                Note n = snapshot.toObject(Note.class);
                                mNoteList.add(n);
                            }

                            mNoteAdapter.addAll(mNoteList);
                            mNoteAdapter.notifyDataSetChanged();
                            notesListView.setAdapter(mNoteAdapter);
                        }
                    }
                });

        // button to another activity for creating a note
        ImageView imageAddNoteMain = notesFragmentView.findViewById(R.id.addNote);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), NotesCreate.class),
                        REQUEST_CODE_ADD_NOTE);
            }
        });

        // return view
        return notesFragmentView;
    }
}
