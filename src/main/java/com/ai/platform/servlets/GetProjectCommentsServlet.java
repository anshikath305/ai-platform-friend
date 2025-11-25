package com.ai.platform.servlets;

import com.ai.platform.dao.CollabCommentDAO;
import com.ai.platform.model.CollabComment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/get-comments")
public class GetProjectCommentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int projectId = Integer.parseInt(request.getParameter("project_id"));

        CollabCommentDAO dao = new CollabCommentDAO();
        List<CollabComment> comments = dao.getCommentsForProject(projectId);

        response.setContentType("text/html");

        StringBuilder outHtml = new StringBuilder();

        for (CollabComment c : comments) {
            outHtml.append("<div class='comment-card'>")
                   .append("<b>").append(dao.getUserName(c.getUserId())).append("</b><br>")
                   .append("<small>").append(c.getCreatedAt()).append("</small>")
                   .append("<p>").append(c.getComment()).append("</p>")
                   .append("</div>");
        }

        response.getWriter().write(outHtml.toString());
    }
}
