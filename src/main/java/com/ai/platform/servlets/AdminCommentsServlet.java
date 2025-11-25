package com.ai.platform.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.ai.platform.dao.CollabCommentDAO;
import com.ai.platform.model.CollabComment;

@WebServlet("/admin/comments")
public class AdminCommentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CollabCommentDAO dao = new CollabCommentDAO();
        List<CollabComment> comments = dao.getAllComments();

        request.setAttribute("comments", comments);
        request.getRequestDispatcher("admin-comments.jsp").forward(request, response);
    }
}
