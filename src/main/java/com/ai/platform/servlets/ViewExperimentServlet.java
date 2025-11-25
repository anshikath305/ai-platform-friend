package com.ai.platform.servlets;

import com.ai.platform.dao.ExperimentDAO;
import com.ai.platform.dao.ExperimentLogDAO;
import com.ai.platform.dao.ExperimentResultDAO;
import com.ai.platform.model.Experiment;
import com.ai.platform.model.ExperimentLog;
import com.ai.platform.model.ExperimentResult;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/view-experiment")
public class ViewExperimentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int expId = Integer.parseInt(request.getParameter("id"));

        ExperimentDAO expDao = new ExperimentDAO();
        ExperimentLogDAO logDao = new ExperimentLogDAO();
        ExperimentResultDAO resultDao = new ExperimentResultDAO();

        Experiment experiment = expDao.getExperimentById(expId);
        List<ExperimentLog> logs = logDao.getLogs(expId);
        List<ExperimentResult> results = resultDao.getResults(expId);

        request.setAttribute("experiment", experiment);
        request.setAttribute("logs", logs);
        request.setAttribute("results", results);

        request.getRequestDispatcher("experiment-details.jsp").forward(request, response);
    }
}
