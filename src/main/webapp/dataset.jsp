<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.model.Dataset" %>
<%@ page import="com.ai.platform.dao.DatasetDAO" %>
<%@ page import="com.ai.platform.model.User" %>

<%
    User user = (User) session.getAttribute("user");
    DatasetDAO dao = new DatasetDAO();
    List<Dataset> datasets = dao.getDatasetsByUser(user.getId());
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dataset Management</title>
    <link rel="stylesheet" href="assets/css/style.css">

</head>
<body>

<h1>Dataset Management</h1>

<form action="upload-dataset" method="post" enctype="multipart/form-data">
    <label>Name:</label><br>
    <input type="text" name="name" required><br><br>

    <label>Description:</label><br>
    <textarea name="description"></textarea><br><br>

    <label>Upload File:</label><br>
    <input type="file" name="dataset_file" required><br><br>

    <button type="submit">Upload Dataset</button>
</form>

<br><h2>Your Datasets</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>File</th>
        <th>Uploaded At</th>
        <th>Action</th>
    </tr>

    <% for (Dataset d : datasets) { %>
        <tr>
            <td><%= d.getId() %></td>
            <td><%= d.getName() %></td>
            <td><%= d.getDescription() %></td>
            <td><a href="<%= d.getFilePath() %>" target="_blank">Download</a></td>
            <td><%= d.getUploadedAt() %></td>
            <td>
                <a href="delete-dataset?id=<%= d.getId() %>">Delete</a>
            </td>
        </tr>
    <% } %>

</table>

</body>
</html>
