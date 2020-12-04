package com.example.csci_455_student_productivity_and_reflection_app.courses;

public class Course {

        private String title;
        private double currentAverage;

        public Course() {
            //Empty constructor needed for firebase
        }

        public Course(String title, double currentAverage) {
            this.title = title;
            this.currentAverage = currentAverage;

        }

        public String getTitle() {
            return title;
        }


         public double getCurrentAverage() {
              return currentAverage;
         }

}
