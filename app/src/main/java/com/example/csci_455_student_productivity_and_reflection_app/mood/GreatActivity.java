package com.example.csci_455_student_productivity_and_reflection_app.mood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.csci_455_student_productivity_and_reflection_app.MoodFragment;
import com.example.csci_455_student_productivity_and_reflection_app.R;

public class GreatActivity extends AppCompatActivity {
    public ImageView GREAT_ICON;
    String imageString = "great_icon";
    public static int resID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_great);

        // getting the string of an image to put in the database to display the image of the mood on the dashboard
        resID = getResources().getIdentifier(imageString, "id", getPackageName());
        GREAT_ICON = findViewById(resID);



        // Saving the mood (doesn't work quite yet)
//        Button send = findViewById(R.id.SaveButton);
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment mFragment = new MoodFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.moodfrag, mFragment).commit();




                // can't use Intents to start up a Fragment class
//                Intent intent = new Intent(GreatActivity.this, MoodFragment.class);
//                intent.putExtra("my_image", R.drawable.ic_great_icon);
//                startActivity(intent);
//            }
//        });

    }
}