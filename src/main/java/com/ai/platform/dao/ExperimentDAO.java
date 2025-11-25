package com.ai.platform.dao;

import com.ai.platform.db.DBConnection;
import com.ai.platform.model.Experiment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExperimentDAO {

    // Create experiment
    public boolean createExperiment(Experiment exp) {

        String sql = "INSERT INTO experiments (user_id, title, description) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, exp.getUserId());
            stmt.setString(2, exp.getTitle());
            stmt.setString(3, exp.getDescription());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get experiments by user
    public List<Experiment> getExperimentsByUser(int userId) {
        List<Experiment> list = new ArrayList<>();

        String sql = "SELECT * FROM experiments WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Experiment exp = new Experiment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("created_at")
                );

                exp.setStatus(rs.getString("status"));
                exp.setAccuracy(rs.getFloat("accuracy"));

                list.add(exp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get experiment by ID
    public Experiment getExperimentById(int id) {

        String sql = "SELECT * FROM experiments WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Experiment exp = new Experiment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("created_at")
                );

                exp.setStatus(rs.getString("status"));
                exp.setAccuracy(rs.getFloat("accuracy"));
                return exp;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update experiment status
    public void updateStatus(int experimentId, String status) {

        String sql = "UPDATE experiments SET status=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, experimentId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update experiment accuracy
    public void updateAccuracy(int experimentId, float accuracy) {

        String sql = "UPDATE experiments SET accuracy=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, accuracy);
            stmt.setInt(2, experimentId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add log entry
    public void addLog(int experimentId, String message) {

        String sql = "INSERT INTO experiment_logs (experiment_id, message) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, experimentId);
            stmt.setString(2, message);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get log entries
    public List<String> getLogs(int experimentId) {
        List<String> logs = new ArrayList<>();

        String sql = "SELECT message FROM experiment_logs WHERE experiment_id = ? ORDER BY created_at ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, experimentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                logs.add(rs.getString("message"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logs;
    }

    // ADMIN: Fetch all experiments with user name
    public List<Experiment> getAllExperiments() {
        List<Experiment> list = new ArrayList<>();

        String sql = 
            "SELECT e.*, u.name AS user_name " +
            "FROM experiments e " +
            "JOIN users u ON e.user_id = u.id " +
            "ORDER BY e.created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Experiment exp = new Experiment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("created_at")
                );

                exp.setStatus(rs.getString("status"));
                exp.setAccuracy(rs.getFloat("accuracy"));
                exp.setUserName(rs.getString("user_name"));

                list.add(exp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Delete Experiment
    public boolean deleteExperiment(int id) {
        String sql = "DELETE FROM experiments WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
