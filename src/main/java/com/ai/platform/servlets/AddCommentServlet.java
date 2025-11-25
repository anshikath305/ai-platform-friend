package com.ai.platform.servlets;

import com.ai.platform.dao.CollabCommentDAO;
import com.ai.platform.model.CollabComment;
import com.ai.platform.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int projectId = Integer.parseInt(request.getParameter("project_id"));
        String commentText = request.getParameter("comment");

        CollabComment comment = new CollabComment();
        comment.setProjectId(projectId);
        comment.setUserId(user.getId());
        comment.setComment(commentText);

        CollabCommentDAO dao = new CollabCommentDAO();
        dao.createComment(comment);

        response.sendRedirect("project-details.jsp?id=" + projectId);
    }
}
