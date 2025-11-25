package com.ai.platform.dao;

import com.ai.platform.db.DBConnection;
import com.ai.platform.model.TrainingJob;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainingJobDAO {

    // CREATE JOB & RETURN GENERATED ID
    public int createTrainingJob(TrainingJob job) {
        String sql = "INSERT INTO training_jobs (dataset_id, model_name, parameters, created_by, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, job.getDatasetId());
            stmt.setString(2, job.getModelName());
            stmt.setString(3, job.getParameters());
            stmt.setInt(4, job.getCreatedBy());
            stmt.setString(5, job.getStatus());

            stmt.executeUpdate();

            // return generated job ID
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;  // failed
    }

    // GET ALL JOBS FOR USER
    public List<TrainingJob> getTrainingJobs(int userId) {
        List<TrainingJob> list = new ArrayList<>();

        String sql = "SELECT * FROM training_jobs WHERE created_by = ? ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TrainingJob job = new TrainingJob(
                        rs.getInt("id"),
                        rs.getInt("dataset_id"),
                        rs.getString("model_name"),
                        rs.getString("parameters"),
                        rs.getFloat("accuracy"),
                        rs.getString("status"),
                        rs.getInt("created_by"),
                        rs.getString("created_at")
                );

                // 🔥 Important line you asked for:
                job.setCreatedAt(rs.getString("created_at"));

                list.add(job);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE STATUS
    public void updateStatus(int jobId, String status) {
        String sql = "UPDATE training_jobs SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, jobId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE ACCURACY
    public void updateAccuracy(int jobId, float accuracy) {
        String sql = "UPDATE training_jobs SET accuracy = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, accuracy);
            stmt.setInt(2, jobId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getLastInsertedJobId() {
    String sql = "SELECT LAST_INSERT_ID() as id";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            return rs.getInt("id");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1;
}

public boolean deleteTrainingJob(int id) {
    String sql = "DELETE FROM training_jobs WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
public List<TrainingJob> getAllTrainingJobs() {
    List<TrainingJob> list = new ArrayList<>();

    String sql = "SELECT * FROM training_jobs ORDER BY created_at DESC";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            TrainingJob job = new TrainingJob();
            job.setId(rs.getInt("id"));
            job.setCreatedBy(rs.getInt("created_by"));
            job.setDatasetId(rs.getInt("dataset_id"));
            job.setModelName(rs.getString("model_name"));
            job.setStatus(rs.getString("status"));
            job.setAccuracy(rs.getFloat("accuracy"));
            job.setCreatedAt(rs.getString("created_at"));

            list.add(job);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}




}
