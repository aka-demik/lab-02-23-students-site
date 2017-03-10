package com.aka.models;

public class ScheduledLecture {

    private long id;
    private Lecture lecture;
    private java.util.Date scheduledAt;

    public ScheduledLecture() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public java.util.Date getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(java.util.Date scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

}
