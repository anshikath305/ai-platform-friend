package com.ai.platform.servlets;

import com.ai.platform.dao.ExperimentDAO;
import com.ai.platform.model.Experiment;
import com.ai.platform.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/create-experiment")
public class CreateExperimentServlet extends HttpServlet {

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

        Experiment exp = new Experiment();
        exp.setUserId(user.getId());
        exp.setTitle(title);
        exp.setDescription(description);

        ExperimentDAO dao = new ExperimentDAO();
        dao.createExperiment(exp);

        response.sendRedirect("experiments.jsp?created=1");
    }
}
