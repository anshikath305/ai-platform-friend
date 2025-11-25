package com.ai.platform.filters;

import com.ai.platform.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();
        User user = (User) request.getSession().getAttribute("user");


        // --------------------------------------
        // 1️⃣ Publicly Accessible Pages
        // --------------------------------------
        if (
                path.contains("login.jsp") ||
                path.contains("signup.jsp") ||
                path.endsWith("/login") ||
                path.contains("assets/") ||
                path.contains("error403.jsp")
        ) {
            chain.doFilter(req, res);
            return;
        }


        // --------------------------------------
        // 2️⃣ Block All Other Pages If Not Logged In
        // --------------------------------------
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }


        // --------------------------------------
        // 3️⃣ ADMIN Pages Protection
        // Match ANY URL containing "admin-"
        // OR any servlet starting with "/admin"
        // --------------------------------------
        boolean isAdminPage =
                path.contains("admin-") ||  // e.g. admin-users.jsp
                path.contains("/admin");    // e.g. /admin-users, /admin-experiments

        if (isAdminPage) {
            if (!"ADMIN".equals(user.getRole())) {
                response.sendRedirect("error403.jsp");
                return;
            }
        }


        // --------------------------------------
        // 4️⃣ RESEARCHER Pages Protection
        // --------------------------------------
        boolean isResearcherPage =
                path.contains("researcher-") ||
                path.contains("/researcher");

        if (isResearcherPage) {
            if (!"RESEARCHER".equals(user.getRole())) {
                response.sendRedirect("error403.jsp");
                return;
            }
        }


        // --------------------------------------
        // 5️⃣ Allow Access
        // --------------------------------------
        chain.doFilter(req, res);
    }
}
