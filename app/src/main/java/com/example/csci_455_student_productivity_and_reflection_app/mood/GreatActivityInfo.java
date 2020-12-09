package com.example.csci_455_student_productivity_and_reflection_app.mood;

public class GreatActivityInfo {

    private String dateInfo;
    private String textInfo;
    private String colorInfo;


    public GreatActivityInfo(){

    }

    public GreatActivityInfo(String dateInfo, String textInfo, String colorInfo) {
        this.dateInfo = dateInfo;
        this.textInfo = textInfo;
        this.colorInfo = colorInfo;
    }

    public String getDateInfo() {
        return dateInfo;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public String getColorInfo(){
        return colorInfo;
    }

    public void setDateInfo(String dateInfo) {
        this.dateInfo = dateInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }

    public void setColorInfo(String colorInfo) {
        this.colorInfo = colorInfo;
    }
}


