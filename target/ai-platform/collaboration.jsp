<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.model.User" %>
<%@ page import="com.ai.platform.model.Project" %>
<%@ page import="com.ai.platform.dao.ProjectDAO" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    ProjectDAO dao = new ProjectDAO();
    List<Project> projects = dao.getAllProjects();   // Later we will switch to getProjectsByUser()
%>

<!DOCTYPE html>
<html>
<head>
    <title>Collaboration - Projects</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 30px;
        }

        h1 {
            margin-bottom: 20px;
        }

        .create-box {
            background: #f3f4f6;
            padding: 20px;
            border-radius: 8px;
            width: 400px;
            margin-bottom: 30px;
        }

        .project-list {
            margin-top: 20px;
        }

        .project-item {
            background: #ffffff;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 6px;
            margin-bottom: 10px;
        }

        .project-item h3 {
            margin: 0;
        }

        .btn {
            background-color: #2563eb;
            color: white;
            padding: 8px 15px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            margin-top: 10px;
        }

        .btn:hover {
            background-color: #1e40af;
        }

        .delete-btn {
            background-color: #dc2626;
            margin-left: 15px;
        }

        .delete-btn:hover {
            background-color: #991b1b;
        }

        input, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 8px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }
    </style>
</head>

<body>

<h1>Collaboration Projects</h1>

<div class="create-box">
    <h3>Create New Project</h3>

    <form action="create-project" method="post">
        <label>Project Title</label>
        <input type="text" name="title" required>

        <label>Description</label>
        <textarea name="description" required></textarea>

        <button class="btn" type="submit">Create Project</button>
    </form>
</div>

<div class="project-list">
    <h2>All Projects</h2>

    <% for (Project p : projects) { %>
    <div class="project-item">
        <h3><%= p.getTitle() %></h3>
        <p><%= p.getDescription() %></p>
        <p><small>Created At: <%= p.getCreatedAt() %></small></p>

        <a href="project-details.jsp?id=<%= p.getId() %>">
            <button class="btn">Open</button>
        </a>

        <a href="delete-project?id=<%= p.getId() %>">
            <button class="btn delete-btn">Delete</button>
        </a>
    </div>
    <% } %>
</div>

</body>
</html>
