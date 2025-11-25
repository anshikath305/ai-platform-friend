package com.ai.platform.model;

public class TrainingJob {

    private int id;
    private int datasetId;
    private String modelName;
    private String parameters;
    private float accuracy;
    private String status;
    private int createdBy;
    private String createdAt;
    

    public TrainingJob() {}

    public TrainingJob(int id, int datasetId, String modelName, String parameters,
                       float accuracy, String status, int createdBy, String createdAt) {
        this.id = id;
        this.datasetId = datasetId;
        this.modelName = modelName;
        this.parameters = parameters;
        this.accuracy = accuracy;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDatasetId() { return datasetId; }
    public void setDatasetId(int datasetId) { this.datasetId = datasetId; }

    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }

    public String getParameters() { return parameters; }
    public void setParameters(String parameters) { this.parameters = parameters; }

    public float getAccuracy() { return accuracy; }
    public void setAccuracy(float accuracy) { this.accuracy = accuracy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
