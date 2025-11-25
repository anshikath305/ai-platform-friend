package com.ai.platform.servlets;

import com.ai.platform.dao.CollabPostDAO;
import com.ai.platform.model.CollabPost;
import com.ai.platform.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/create-post")
public class CreatePostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String content = request.getParameter("content");

        CollabPost post = new CollabPost();
        post.setUserId(user.getId());
        post.setContent(content);

        CollabPostDAO dao = new CollabPostDAO();
        dao.createPost(post);

        response.sendRedirect("collaboration.jsp");
    }
}
