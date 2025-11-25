<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.model.Experiment" %>

<%
    List<Experiment> experiments = (List<Experiment>) request.getAttribute("experiments");
%>

<!DOCTYPE html>
<html>
<head>
<title>Admin - All Experiments</title>
<link rel="stylesheet" href="assets/css/style.css">

<style>
    body { font-family: Arial; padding: 30px; }
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    th, td { padding: 10px; border-bottom: 1px solid #ccc; }
    th { background: #1f2937; color: white; }
    .btn-delete { background: #dc2626; padding: 6px 10px; color: white; text-decoration: none; border-radius: 5px; }
</style>
</head>
<body>

<h2>All Experiments</h2>

<table>
<tr>
    <th>ID</th>
    <th>User</th>
    <th>Title</th>
    <th>Status</th>
    <th>Accuracy</th>
    <th>Created</th>
    <th>Action</th>
</tr>

<% for (Experiment e : experiments) { %>
<tr>
    <td><%= e.getId() %></td>
    <td><%= e.getUserName() %></td>
    <td><%= e.getTitle() %></td>
    <td><%= e.getStatus() %></td>
    <td><%= e.getAccuracy() %></td>
    <td><%= e.getCreatedAt() %></td>
    <td>
        <a class="btn-delete" href="delete-experiment?id=<%= e.getId() %>">Delete</a>
    </td>
</tr>
<% } %>

</table>

</body>
</html>
