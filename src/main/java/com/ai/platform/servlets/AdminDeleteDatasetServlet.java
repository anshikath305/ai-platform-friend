package com.ai.platform.servlets;

import com.ai.platform.dao.DatasetDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin-delete-dataset")
public class AdminDeleteDatasetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        DatasetDAO dao = new DatasetDAO();
        dao.deleteDataset(id);

        response.sendRedirect("admin-datasets.jsp");
    }
}
