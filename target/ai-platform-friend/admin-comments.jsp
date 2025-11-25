<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.model.CollabComment" %>

<%
    List<CollabComment> comments = (List<CollabComment>) request.getAttribute("comments");
%>

<!DOCTYPE html>
<html>
<head>
<title>Admin - All Comments</title>
<link rel="stylesheet" href="assets/css/style.css">

<style>
    body { font-family: Arial; padding: 30px; }
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    th, td { padding: 10px; border-bottom: 1px solid #ccc; }
    th { background: #1f2937; color: white; }
</style>
</head>
<body>

<h2>All Collaboration Comments</h2>

<table>
<tr>
    <th>ID</th>
    <th>Project</th>
    <th>User</th>
    <th>Comment</th>
    <th>Created</th>
</tr>

<% for (CollabComment c : comments) { %>
<tr>
    <td><%= c.getId() %></td>
    <td><%= c.getProjectTitle() %></td>
    <td><%= c.getUserName() %></td>
    <td><%= c.getComment() %></td>
    <td><%= c.getCreatedAt() %></td>
</tr>
<% } %>

</table>

</body>
</html>
