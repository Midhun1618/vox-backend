package com.voxcom.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    private String userEmail; // Link to User.email
    private String text;
    private boolean done;

    @Indexed(expireAfterSeconds = 86400) // 24 hours
    private Date createdAt = new Date();

    // ---------- Constructors ----------
    public Task() {}

    public Task(String userEmail, String text) {
        this.userEmail = userEmail;
        this.text = text;
        this.done = false;
        this.createdAt = new Date();
    }

    // ---------- Getters & Setters ----------
    public String getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
