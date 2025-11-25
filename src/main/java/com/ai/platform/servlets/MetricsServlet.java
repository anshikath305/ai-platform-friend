package com.ai.platform.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/metrics")
public class MetricsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Sample metrics (epoch-wise)
        List<Double> accuracy = Arrays.asList(0.62, 0.71, 0.78, 0.83, 0.88);
        List<Double> loss = Arrays.asList(1.21, 0.96, 0.65, 0.44, 0.29);
        List<Double> precision = Arrays.asList(0.60, 0.68, 0.73, 0.80, 0.86);
        List<Double> recall = Arrays.asList(0.58, 0.70, 0.75, 0.82, 0.87);

        request.setAttribute("accuracy", accuracy);
        request.setAttribute("loss", loss);
        request.setAttribute("precision", precision);
        request.setAttribute("recall", recall);

        request.getRequestDispatcher("metrics.jsp").forward(request, response);
    }
}
