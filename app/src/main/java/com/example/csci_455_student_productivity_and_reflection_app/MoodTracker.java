package com.example.csci_455_student_productivity_and_reflection_app;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.media.Image;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MoodTracker extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mood);

        //The Function of the Button
        //Create the Floating Action Button
        final ImageView fabIconNew = new ImageView(this);
        //Set the icon in the center of the Floating Action Button
        fabIconNew.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button));

        //Placement of the button's position
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                .build();

        //Create selected moods which are also the Floating Action Button
        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        //Create an image view for each selected mood item
        ImageView menuOption1 = new ImageView(this);
        ImageView menuOption2 = new ImageView(this);
        ImageView menuOption3 = new ImageView(this);
        ImageView menuOption4 = new ImageView(this);

        //Set the icon for each mood
        menuOption1.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_great_mood));
        menuOption2.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_good_mood));
        menuOption3.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_okay_mood));
        menuOption4.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_sad_mood));

        //Build the menu with default options: 90 degrees, 72dp radius
        //Setting 4 default SubActionButtons
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(menuOption1).build())
                .addSubActionView(rLSubBuilder.setContentView(menuOption2).build())
                .addSubActionView(rLSubBuilder.setContentView(menuOption3).build())
                .addSubActionView(rLSubBuilder.setContentView(menuOption4).build())
                .attachTo(rightLowerButton)
                .build();

        //Animation and rotation of the button
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                //Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                //Rotating the icon the opposite direction
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
        });

        //Click on each item/mood
        menuOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Option 1", Toast.LENGTH_SHORT).show();
            }
        });

        menuOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Option 2", Toast.LENGTH_SHORT).show();
            }
        });

        menuOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Option 3", Toast.LENGTH_SHORT).show();
            }
        });

        menuOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Option 4", Toast.LENGTH_SHORT).show();
            }
        });
    }



}


