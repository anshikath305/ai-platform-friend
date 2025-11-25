package com.ai.platform.worker;

import com.ai.platform.dao.ExperimentDAO;

public class ExperimentWorker implements Runnable {

    private int experimentId;

    public ExperimentWorker(int experimentId) {
        this.experimentId = experimentId;
    }

    @Override
    public void run() {
        try {
            ExperimentDAO dao = new ExperimentDAO();

            // Mark experiment as running
            dao.updateStatus(experimentId, "RUNNING");
            dao.addLog(experimentId, "Experiment started…");

            Thread.sleep(1000);

            dao.addLog(experimentId, "Loading dataset…");
            Thread.sleep(1000);

            dao.addLog(experimentId, "Preprocessing data…");
            Thread.sleep(1000);

            dao.addLog(experimentId, "Training model (Step 1/3)…");
            Thread.sleep(1500);

            dao.addLog(experimentId, "Training model (Step 2/3)…");
            Thread.sleep(1500);

            dao.addLog(experimentId, "Training model (Step 3/3)…");
            Thread.sleep(1500);

            dao.addLog(experimentId, "Evaluating model accuracy…");
            Thread.sleep(2000);

            float accuracy = (float) (80 + Math.random() * 20); // 80%–100%
            dao.updateAccuracy(experimentId, accuracy);

            dao.addLog(experimentId, "Model accuracy: " + accuracy + "%");

            dao.updateStatus(experimentId, "COMPLETED");
            dao.addLog(experimentId, "Experiment completed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            ExperimentDAO dao = new ExperimentDAO();
            dao.addLog(experimentId, "Experiment failed due to error.");
            dao.updateStatus(experimentId, "FAILED");
        }
    }
}
