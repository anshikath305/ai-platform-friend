package com.ai.platform.servlets;

import com.ai.platform.dao.ProjectDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-project")
public class DeleteProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        ProjectDAO dao = new ProjectDAO();
        dao.deleteProject(id);

        response.sendRedirect("collaboration.jsp?deleted=1");
    }
}
