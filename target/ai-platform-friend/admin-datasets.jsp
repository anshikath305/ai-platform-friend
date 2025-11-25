<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.dao.DatasetDAO" %>
<%@ page import="com.ai.platform.model.Dataset" %>
<%@ page import="com.ai.platform.model.User" %>

<%
    User admin = (User) session.getAttribute("user");
    if (admin == null || !"ADMIN".equals(admin.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    DatasetDAO dao = new DatasetDAO();
    List<Dataset> datasets = dao.getAllDatasets();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin – All Datasets</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #1f2937;
            color: white;
        }

        .delete-btn {
            background-color: #dc2626;
            color: white;
            padding: 6px 12px;
            border-radius: 5px;
            text-decoration: none;
        }

        .delete-btn:hover {
            background-color: #b91c1c;
        }
    </style>
</head>
<body>

<h1>All Uploaded Datasets</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>File</th>
        <th>Uploaded By</th>
        <th>Uploaded At</th>
        <th>Action</th>
    </tr>

    <% for (Dataset d : datasets) { %>
    <tr>
        <td><%= d.getId() %></td>
        <td><%= d.getName() %></td>
        <td><%= d.getDescription() %></td>
        <td><%= d.getFilePath() %></td>
        <td><%= d.getUploaderName() %></td>
        <td><%= d.getUploadedAt() %></td>

        <td>
            <a class="delete-btn" href="admin-delete-dataset?id=<%= d.getId() %>">
                Delete
            </a>
        </td>
    </tr>
    <% } %>

</table>

</body>
</html>
