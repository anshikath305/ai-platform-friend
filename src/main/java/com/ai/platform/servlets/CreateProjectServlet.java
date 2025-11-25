package com.ai.platform.servlets;

import com.ai.platform.dao.ProjectDAO;
import com.ai.platform.model.Project;
import com.ai.platform.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/create-project")
public class CreateProjectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Project p = new Project();
        p.setTitle(title);
        p.setDescription(description);
        p.setCreatedBy(user.getId());

        ProjectDAO dao = new ProjectDAO();
        dao.createProject(p);

        response.sendRedirect("collaboration.jsp?created=1");
    }
}
