package com.example.csci_455_student_productivity_and_reflection_app.courses;

public class Course {

        private String title;
        private double currentGrade;

        public Course() {
            //Empty constructor needed for firebase
        }

        public Course(String title, double currentGrade) {
            this.title = title;
            this.currentGrade = currentGrade;

        }

        public String getTitle() {
            return title;
        }


         public double getCurrentGrade() {
              return currentGrade;
         }

}
