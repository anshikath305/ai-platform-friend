package com.ai.platform.training;

import com.ai.platform.dao.TrainingJobDAO;
import com.ai.platform.model.TrainingJob;

public class TrainingSimulator {

    public void startTraining(TrainingJob job) {
        TrainingJobDAO dao = new TrainingJobDAO();

        try {
            // Step 1 — Mark job as RUNNING
            dao.updateStatus(job.getId(), "RUNNING");

            // Step 2 — Simulate training loop
            float accuracy = 0f;

            for (int i = 1; i <= 10; i++) {
                Thread.sleep(1000); // simulate training time

                accuracy += (Math.random() * 10); // random accuracy increase
                if (accuracy > 100) accuracy = 100;

                dao.updateAccuracy(job.getId(), accuracy);
            }

            // Step 3 — Mark as COMPLETED
            dao.updateStatus(job.getId(), "COMPLETED");

        } catch (Exception e) {
            e.printStackTrace();
            dao.updateStatus(job.getId(), "FAILED");
        }
    }
}
