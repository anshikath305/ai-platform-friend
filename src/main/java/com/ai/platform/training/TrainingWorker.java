package com.ai.platform.training;

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

            // Step 1: Status → RUNNING
            dao.updateStatus(jobId, "RUNNING");
            float accuracy = 0;

            // Step 2: Update every 3 seconds
            while (accuracy < 100) {
                Thread.sleep(3000); // 3 seconds
                accuracy += 10;

                dao.updateAccuracy(jobId, accuracy);
            }

            // Step 3: Status → COMPLETED
            dao.updateStatus(jobId, "COMPLETED");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
