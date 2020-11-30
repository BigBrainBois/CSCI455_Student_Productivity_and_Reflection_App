package com.example.csci_455_student_productivity_and_reflection_app.tasks;

import androidx.annotation.NonNull;

import com.google.firebase.database.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Date;

import javax.annotation.Nullable;

public class Task {
    private String title, description;
    Date date;

    public Task() {
        //Empty constructor needed for firebase
    }

    public Task(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

}
