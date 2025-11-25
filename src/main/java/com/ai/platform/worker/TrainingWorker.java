package com.ai.platform.worker;

import com.ai.platform.dao.TrainingJobDAO;

public class TrainingWorker implements Runnable {

    private int jobId;

    public TrainingWorker(int jobId) {
        this.jobId = jobId;
    }

    @Override
    public void run() {
        try {
            TrainingJobDAO dao = new TrainingJobDAO();

            // Step 1: Mark job as running
            dao.updateStatus(jobId, "RUNNING");
            Thread.sleep(2000); // simulate delay

            // Step 2: simulate model training progress
            float accuracy = (float) (Math.random() * 40 + 60);  // random 60–100%
            Thread.sleep(3000);

            // Step 3: update accuracy
            dao.updateAccuracy(jobId, accuracy);

            // Step 4: complete job
            dao.updateStatus(jobId, "COMPLETED");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
