package com.example.csci_455_student_productivity_and_reflection_app.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.csci_455_student_productivity_and_reflection_app.MoodFragment;
import com.example.csci_455_student_productivity_and_reflection_app.R;

public class GreatActivity extends AppCompatActivity {
//    Button saveButton;
//    ImageView greatImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_great);

        /* Code by Nawshin:
        *     Converting image into bitmap to store in a String value
        * */

//        saveButton = findViewById(R.id.SaveButton);
//        greatImage = findViewById(R.id.great_icon);
//        Bitmap viewBitmap = Bitmap.createBitmap(greatImage.getWidth(),greatImage.getHeight(),Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(viewBitmap);
//        greatImage.setImageBitmap(viewBitmap);
//        BitmapHelper.getInstance().setBitmap(viewBitmap);
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(BitmapHelper.getInstance().getBitmap() == null ){
//                    Toast.makeText(GreatActivity.this, "Bitmap can't null", Toast.LENGTH_SHORT).show();
//
//                } else{
//                    Intent intent = new Intent(GreatActivity.this, MoodFragment.class);
//                    startActivity(intent);
//                }
//
//            }
//        });
    }
}