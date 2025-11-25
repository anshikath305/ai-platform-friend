package com.ai.platform.model;

public class Experiment {

    private int id;
    private int userId;
    private String title;
    private String description;
    private String createdAt;
    private String status;
    private float accuracy;

    // NEW FIELD for admin panel
    private String userName;

    public Experiment() {}

    public Experiment(int id, int userId, String title, String description, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public float getAccuracy() { return accuracy; }
    public void setAccuracy(float accuracy) { this.accuracy = accuracy; }

    // NEW Getter + Setter for userName
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}
