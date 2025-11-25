package com.ai.platform.dao;

import com.ai.platform.db.DBConnection;
import com.ai.platform.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;


public class UserDAO {

    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    public boolean createUser(User user) {
    boolean inserted = false;

    try (Connection conn = DBConnection.getConnection()) {

        String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, user.getRole());

        int rows = stmt.executeUpdate();
        inserted = rows > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return inserted;
}
public List<User> getAllUsers() {
    List<User> list = new ArrayList<>();

    String sql = "SELECT * FROM users ORDER BY id DESC";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            User u = new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role")
            );
            list.add(u);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
public boolean deleteUser(int id) {
    String sql = "DELETE FROM users WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
public User getUserByEmail(String email) {
    String sql = "SELECT * FROM users WHERE email = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
public User getUserById(int id) {
    String sql = "SELECT * FROM users WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setEmail(rs.getString("email"));
            u.setRole(rs.getString("role"));
            return u;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
public boolean updateUserRole(int userId, String role) {
    String sql = "UPDATE users SET role=? WHERE id=?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, role);
        stmt.setInt(2, userId);
        return stmt.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}






}
