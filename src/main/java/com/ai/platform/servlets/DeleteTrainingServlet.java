package com.ai.platform.servlets;

import com.ai.platform.dao.TrainingJobDAO;
import com.ai.platform.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-training")
public class DeleteTrainingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Only admin can delete
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));

        TrainingJobDAO dao = new TrainingJobDAO();
        dao.deleteTrainingJob(id);

        response.sendRedirect("admin-training.jsp");
    }
}
