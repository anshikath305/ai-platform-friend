package com.ai.platform.servlets;

import com.ai.platform.dao.DatasetDAO;
import com.ai.platform.model.Dataset;
import com.ai.platform.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/upload-dataset")
@MultipartConfig
public class UploadDatasetServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get logged-in user
        User user = (User) request.getSession().getAttribute("user");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part filePart = request.getPart("dataset_file");

        // Get original filename
        String fileName = filePart.getSubmittedFileName();

        // Define upload path inside Tomcat webapp
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        // Save file to server
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        // Save dataset info to DB
        Dataset dataset = new Dataset();
        dataset.setName(name);
        dataset.setDescription(description);
        dataset.setFilePath("uploads/" + fileName);
        dataset.setUploadedBy(user.getId());

        DatasetDAO dao = new DatasetDAO();
        dao.insertDataset(dataset);

        response.sendRedirect("dataset.jsp?upload=success");
    }
}
