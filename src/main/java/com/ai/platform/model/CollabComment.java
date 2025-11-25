package com.ai.platform.model;

public class CollabComment {

    private int id;
    private int projectId;
    private int userId;
    private String comment;
    private String createdAt;

    public CollabComment() {}

    public CollabComment(int id, int projectId, int userId, String comment, String createdAt) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
