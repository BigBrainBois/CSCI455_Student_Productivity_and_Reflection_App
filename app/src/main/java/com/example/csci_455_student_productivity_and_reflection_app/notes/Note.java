package com.example.csci_455_student_productivity_and_reflection_app.notes;

import java.lang.reflect.Array;
import java.util.Date;

public class Note {

    private String title;
    private String subtitle;
    private String description;

    public Note() {

    }

    public Note(String title, String subtitle, String description) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }
}
