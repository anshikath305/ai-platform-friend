<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.dao.ProjectDAO" %>
<%@ page import="com.ai.platform.model.Project" %>

<%
    ProjectDAO dao = new ProjectDAO();
    List<Project> projects = dao.getAllProjects();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin – Project Management</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body { font-family: Arial; padding: 30px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border-bottom: 1px solid #ccc; }
        th { background: #1f2937; color: white; }
        a.button {
            padding: 6px 12px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .delete { background: #dc2626; }
    </style>
</head>
<body>

<h2>Project Management</h2>

<a href="admin-create-project.jsp" class="button">+ Create New Project</a>

<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Created By</th>
        <th>Created At</th>
        <th>Actions</th>
    </tr>

    <% for (Project p : projects) { %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getTitle() %></td>
        <td><%= p.getCreatedBy() %></td>
        <td><%= p.getCreatedAt() %></td>
        <td>
            <a class="button" href="admin-assign-members.jsp?project_id=<%= p.getId() %>">Assign Members</a>
            <a class="button delete" href="delete-project?id=<%= p.getId() %>">Delete</a>
        </td>
    </tr>
    <% } %>
</table>

</body>
</html>
