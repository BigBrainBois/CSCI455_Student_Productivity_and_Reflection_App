package com.example.csci_455_student_productivity_and_reflection_app.assignments;

import java.util.Date;

public class Assignment {

    private String title;
    private Date dueDate;
    private double finalGrade, percentGrade;

    public Assignment() {
        //Empty constructor needed for firebase
    }

    public Assignment(String title, Date dueDate, double finalGrade, double percentGrade) {
        this.title = title;
        this.dueDate = dueDate;
        this.finalGrade = finalGrade;
        this.percentGrade = percentGrade;

    }

    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public double getPercentGrade() {
        return percentGrade;
    }
}
