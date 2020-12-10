package com.example.csci_455_student_productivity_and_reflection_app.assignments;

public class Assignment {

    private String title, course, currentGrade, dueDate;
    private double weight;

    public Assignment() {
        //Empty constructor needed for firebase
    }

    public Assignment(String title, String course, String dueDate, String currentGrade, double weight) {
        this.title = title;
        this.course = course;
        this.dueDate = dueDate;
        this.currentGrade = currentGrade;
        this.weight = weight;

    }

    public String getTitle() {
        return title;
    }

    public String getCourse() {
        return course;
    }

    public String getCurrentGrade() {
        return currentGrade;
    }

    public String getDueDate() {
        return dueDate;
    }

    public double getWeight() {
        return weight;
    }
}
