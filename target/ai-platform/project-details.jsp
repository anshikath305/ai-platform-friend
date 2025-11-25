<%@ page import="com.ai.platform.model.Project" %>
<%@ page import="com.ai.platform.model.User" %>
<%@ page import="com.ai.platform.model.CollabComment" %>
<%@ page import="com.ai.platform.dao.ProjectDAO" %>
<%@ page import="com.ai.platform.dao.ProjectMemberDAO" %>
<%@ page import="com.ai.platform.dao.CollabCommentDAO" %>
<%@ page import="java.util.List" %>

<%
    User sessionUser = (User) session.getAttribute("user");
    if (sessionUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int projectId = Integer.parseInt(request.getParameter("id"));

    ProjectDAO projectDAO = new ProjectDAO();
    Project project = null;

    for (Project p : projectDAO.getAllProjects()) {
        if (p.getId() == projectId) {
            project = p;
            break;
        }
    }

    if (project == null) {
        out.println("<h2>Project not found.</h2>");
        return;
    }

    ProjectMemberDAO pmDAO = new ProjectMemberDAO();
    List<User> members = pmDAO.getMemberDetails(projectId);
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= project.getTitle() %> - Details</title>
<link rel="stylesheet" href="assets/css/style.css">

    <style>
        body { font-family: Arial; padding: 30px; }
        h2 { margin-bottom: 10px; }
        .member-card, .comment-card { 
            padding: 10px; border: 1px solid #ddd; 
            margin-bottom: 10px; border-radius: 6px;
        }
        .btn { padding: 10px 15px; background: #2563eb; color: white;
            border: none; margin-top: 15px; text-decoration: none;
            display: inline-block; border-radius: 5px; }
        .btn:hover { background: #1e40af; }
        .back { margin-top: 20px; display: inline-block;}
    </style>

<script>
function loadComments() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "get-comments?project_id=<%= project.getId() %>", true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            document.getElementById("comments-box").innerHTML = xhr.responseText;
        }
    };

    xhr.send();
}

// auto-refresh every 3 secs
setInterval(loadComments, 3000);
window.onload = loadComments;
</script>

</head>
<body>

<h2>Project: <%= project.getTitle() %></h2>
<p><b>Description:</b> <%= project.getDescription() %></p>
<p><b>Created At:</b> <%= project.getCreatedAt() %></p>

<hr>

<!-- MEMBERS SECTION -->
<h3>Members</h3>

<% if (members.isEmpty()) { %>
    <p>No members added yet.</p>
<% } else { %>
    <% for (User u : members) { %>
        <div class="member-card">
            <b><%= u.getName() %></b><br>
            <small><%= u.getEmail() %></small><br>
            Role: <%= u.getRole() %>
        </div>
    <% } %>
<% } %>

<a href="add-member.jsp?project_id=<%= projectId %>" class="btn">Add Member</a>

<hr>

<!-- COMMENT INPUT -->
<h3>Comments</h3>

<form action="add-comment" method="post">
    <input type="hidden" name="project_id" value="<%= projectId %>">

    <textarea name="comment"
              rows="3"
              style="width: 100%; padding: 10px;"
              placeholder="Write your comment..."></textarea>
    <br><br>

    <button type="submit" class="btn">Post Comment</button>
</form>

<br>

<!-- LIVE COMMENTS HERE -->
<div id="comments-box">
    Loading comments...
</div>

<br>
<a href="collaboration.jsp" class="back">← Back to Projects</a>

</body>
</html>
