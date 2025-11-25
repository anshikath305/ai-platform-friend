package com.ai.platform.model;

public class ProjectMember {

    private int id;
    private int projectId;
    private int userId;

    // Getters & Setters
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
