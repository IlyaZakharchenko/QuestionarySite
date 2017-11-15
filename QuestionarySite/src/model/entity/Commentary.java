package model.entity;

import java.sql.Date;
import java.sql.Date;

public class Commentary {

    private int id;

    private String content;

    private User user;

    private int userId;

    private Interview interview;

    private int interviewId;

    private Date Date;

    public Commentary() {

    }

    public Commentary(int id, String content, int userId, int interviewId, Date date) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.interviewId = interviewId;
        Date = date;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getId() {
        return id;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }
}
