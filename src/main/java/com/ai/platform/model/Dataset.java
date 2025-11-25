package com.ai.platform.model;

public class Dataset {

    private int id;
    private String name;
    private String description;
    private String filePath;
    private int uploadedBy;
    private String uploadedAt;

    // Default constructor (REQUIRED)
    public Dataset() {}

    // Full constructor
    public Dataset(int id, String name, String description,
                   String filePath, int uploadedBy, String uploadedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.filePath = filePath;
        this.uploadedBy = uploadedBy;
        this.uploadedAt = uploadedAt;
    }

    // NEW FIELD for admin dataset overview
    private String uploaderName;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public int getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(int uploadedBy) { this.uploadedBy = uploadedBy; }

    public String getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(String uploadedAt) { this.uploadedAt = uploadedAt; }

    public String getUploaderName() { return uploaderName; }
    public void setUploaderName(String uploaderName) { this.uploaderName = uploaderName; }
}

