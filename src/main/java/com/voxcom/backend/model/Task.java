package com.voxcom.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    private String userEmail;
    private String text;
    private boolean done;
    private Instant createdAt = Instant.now();

    // getters & setters
    public String getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public String getText() { return text; }
    public boolean isDone() { return done; }
    public Instant getCreatedAt() { return createdAt; }

    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setText(String text) { this.text = text; }
    public void setDone(boolean done) { this.done = done; }
}
