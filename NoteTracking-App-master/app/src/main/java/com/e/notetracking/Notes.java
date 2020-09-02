package com.example.notetracking;

public class Notes {
    private long ID;
    private String subject;
    private String details;
    private String date;
    private String time;

    Notes(){}
    Notes(String subject, String details, String date, String time ){
        this.subject = subject;
        this.details = details;
        this.date = date;
        this.time = time;
    }
    Notes(long id,String subject, String details, String date, String time ){
        this.ID = id;
        this.subject = subject;
        this.details = details;
        this.date = date;
        this.time= time;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return details;
    }

    public void setDetail(String detail) {
        this.details = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return date;
    }

    public void setTime(String time) {
        this.date = date;
    }
}
