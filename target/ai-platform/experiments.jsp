<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.dao.ExperimentDAO" %>
<%@ page import="com.ai.platform.model.Experiment" %>
<%@ page import="com.ai.platform.model.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    ExperimentDAO dao = new ExperimentDAO();
    List<Experiment> experiments = dao.getExperimentsByUser(user.getId());
%>

<!DOCTYPE html>
<html>
<head>
    <title>Experiments</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body { font-family: Arial; padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border-bottom: 1px solid #ddd; }
        th { background: #1f2937; color: white; }
        button { padding: 8px 16px; background: #2563eb; color: white; border: none; border-radius: 5px; }
        .new-btn { margin-bottom: 20px; background: #16a34a; }
    </style>
</head>
<body>

<h1>Your Experiments</h1>

<a href="new-experiment.jsp">
    <button class="new-btn">➕ Create New Experiment</button>
</a>

<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Created</th>
        <th>Actions</th>
    </tr>

    <% for (Experiment exp : experiments) { %>
        <tr>
            <td><%= exp.getId() %></td>
            <td><%= exp.getTitle() %></td>
            <td><%= exp.getCreatedAt() %></td>
            <td>
                <a href="view-experiment?id=<%= exp.getId() %>">
                    <button>View</button>
                </a>
            </td>
        </tr>
    <% } %>

</table>

</body>
</html>
