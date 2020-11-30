package com.example.csci_455_student_productivity_and_reflection_app.courses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.csci_455_student_productivity_and_reflection_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddCourse extends AppCompatActivity {

    // declare variables
    private EditText addText;
    private FloatingActionButton addButton;
    private ListView listView;

    // for adding courses
    ArrayAdapter<String> adapter;
    ArrayList<String> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        addText = findViewById(R.id.addText);
        addButton = findViewById(R.id.addButton);
        listView = findViewById(R.id.course_list);

        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(AddCourse.this, R.layout.course_items, itemList);


        // adding courses into the page
        View.OnClickListener addListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.add(addText.getText().toString());
                addText.setText("");
                adapter.notifyDataSetChanged();
            }
        };
        addButton.setOnClickListener(addListener);
        listView.setAdapter(adapter);

    }
}