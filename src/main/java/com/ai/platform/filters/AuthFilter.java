package com.ai.platform.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({
        "/researcher-dashboard.jsp",
        "/admin-dashboard.jsp",
        "/dataset.jsp",
        "/training.jsp",
        "/experiments.jsp",
        "/collaboration.jsp",
        "/profile.jsp",
        "/upload-dataset",
        "/delete-dataset"
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
