/*
* This class is used for helping to convert images into bitmap.
* We're trying to convert the mood image into bitmap to store in a String variable
*    to display the image in the mood fragment
* */

package com.example.csci_455_student_productivity_and_reflection_app.mood;

import android.graphics.Bitmap;

public class BitmapHelper {

    private Bitmap bitmap = null;
    private static final BitmapHelper instance = new BitmapHelper();

    public BitmapHelper() {
    }

    public static BitmapHelper getInstance(){
        return instance;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}