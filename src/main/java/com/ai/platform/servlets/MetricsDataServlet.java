package com.ai.platform.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/metrics-data")
public class MetricsDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");

        // Hardcoded sample values (replace later with DB values)
        String json = "{"
                + "\"accuracy\": [0.62, 0.71, 0.78, 0.83, 0.88],"
                + "\"loss\": [1.21, 0.96, 0.65, 0.44, 0.29],"
                + "\"precision\": [0.60, 0.68, 0.73, 0.80, 0.86],"
                + "\"recall\": [0.58, 0.70, 0.75, 0.82, 0.87]"
                + "}";

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
