package com.ai.platform.model;

public class ExperimentLog {

    private int id;
    private int experimentId;
    private String logText;
    private String createdAt;

    public ExperimentLog() {}

    public ExperimentLog(int id, int experimentId, String logText, String createdAt) {
        this.id = id;
        this.experimentId = experimentId;
        this.logText = logText;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getExperimentId() { return experimentId; }
    public void setExperimentId(int experimentId) { this.experimentId = experimentId; }

    public String getLogText() { return logText; }
    public void setLogText(String logText) { this.logText = logText; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
