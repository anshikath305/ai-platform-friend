package com.ai.platform.servlets;

import com.ai.platform.dao.ExperimentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/get-experiment-logs")
public class GetExperimentLogsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int experimentId = Integer.parseInt(request.getParameter("id"));

        ExperimentDAO dao = new ExperimentDAO();
        List<String> logs = dao.getLogs(experimentId);   // <-- correct type

        request.setAttribute("logs", logs);
        request.getRequestDispatcher("experiment-details.jsp").forward(request, response);
    }
}
