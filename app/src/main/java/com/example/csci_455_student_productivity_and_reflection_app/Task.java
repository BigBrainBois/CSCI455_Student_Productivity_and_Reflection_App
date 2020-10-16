package com.example.csci_455_student_productivity_and_reflection_app;

import java.lang.reflect.Array;
import java.util.Date;

public class Task {
    private Array checkIn;
    private boolean isComplete;
    private String journalID;
    private Date startDate;

    public Task(){
        //Empty constructor needed for firebase
    }

    public Task(Array checkIn, boolean isComplete, String journalID, Date startDate){
        this.checkIn = checkIn;
        this.isComplete = isComplete;
        this.journalID = journalID;
        this.startDate = startDate;
    }

    public Array getCheckIn() {
        return checkIn;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String getJournalID() {
        return journalID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setCheckIn(Array checkIn) {
        this.checkIn = checkIn;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setJournalID(String journalID) {
        this.journalID = journalID;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
