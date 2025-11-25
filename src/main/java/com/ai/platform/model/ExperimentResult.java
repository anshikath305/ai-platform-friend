package com.ai.platform.model;

public class ExperimentResult {

    private int id;
    private int experimentId;
    private String metricName;
    private String metricValue;
    private String createdAt;

    public ExperimentResult() {}

    public ExperimentResult(int id, int experimentId, String metricName, String metricValue, String createdAt) {
        this.id = id;
        this.experimentId = experimentId;
        this.metricName = metricName;
        this.metricValue = metricValue;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getExperimentId() { return experimentId; }
    public void setExperimentId(int experimentId) { this.experimentId = experimentId; }

    public String getMetricName() { return metricName; }
    public void setMetricName(String metricName) { this.metricName = metricName; }

    public String getMetricValue() { return metricValue; }
    public void setMetricValue(String metricValue) { this.metricValue = metricValue; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
