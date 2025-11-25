package com.ai.platform.servlets;

import com.ai.platform.dao.TrainingJobDAO;
import com.ai.platform.model.TrainingJob;
import com.ai.platform.model.User;
import com.ai.platform.worker.TrainingWorker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/train-model")
public class TrainingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int datasetId = Integer.parseInt(request.getParameter("dataset_id"));
            String modelName = request.getParameter("model_name");
            String parameters = request.getParameter("parameters");

            TrainingJob job = new TrainingJob();
            job.setDatasetId(datasetId);
            job.setModelName(modelName);
            job.setParameters(parameters);
            job.setCreatedBy(user.getId());

            TrainingJobDAO dao = new TrainingJobDAO();

            // ❗ DAO now returns jobId, not boolean
            int jobId = dao.createTrainingJob(job);

            if (jobId > 0) {
                // Start background training thread
                TrainingWorker worker = new TrainingWorker(jobId);
                Thread thread = new Thread(worker);
                thread.start();

                response.sendRedirect("training.jsp?success=1");
            } else {
                response.sendRedirect("training.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("training.jsp?error=1");
        }
    }
}
