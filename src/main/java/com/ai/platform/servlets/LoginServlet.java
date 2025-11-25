package com.ai.platform.servlets;

import com.ai.platform.dao.UserDAO;
import com.ai.platform.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmailAndPassword(email, password);

        if (user != null) {

            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect based on role
            if ("ADMIN".equals(user.getRole())) {
                response.sendRedirect("admin-dashboard.jsp");
            } else {
                response.sendRedirect("researcher-dashboard.jsp");
            }

        } else {
            // Wrong credentials
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
