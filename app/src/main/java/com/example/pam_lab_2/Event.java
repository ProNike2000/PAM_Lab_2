package com.example.pam_lab_2;

import java.io.Serializable;

public class Event implements Serializable {
    private String timestamp;
    private String date;
    private String timeStart;
    private String timeEnd;
    private String title;

    public Event(String timestamp, String date, String timeStart, String timeEnd, String title)
    {
        this.timestamp = timestamp;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }
}
