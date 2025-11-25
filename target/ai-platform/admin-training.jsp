<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.dao.TrainingJobDAO" %>
<%@ page import="com.ai.platform.dao.UserDAO" %>
<%@ page import="com.ai.platform.model.TrainingJob" %>
<%@ page import="com.ai.platform.model.User" %>

<%
    User admin = (User) session.getAttribute("user");
    if (admin == null || !"ADMIN".equals(admin.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    TrainingJobDAO dao = new TrainingJobDAO();
    List<TrainingJob> jobs = dao.getAllTrainingJobs();

    UserDAO userDAO = new UserDAO();
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Training Jobs</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <style>
        body { font-family: Arial; padding: 30px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border-bottom: 1px solid #ddd; }
        th { background: #1f2937; color: white; }
        .btn-del {
            background: #dc2626; color: white;
            padding: 6px 12px; border-radius: 4px;
            text-decoration: none;
        }
        .btn-del:hover { background: #b91c1c; }
    </style>
</head>
<body>

<h2>All Training Jobs</h2>
<p>Every model training job executed by all users.</p>

<table>
    <tr>
        <th>ID</th>
        <th>User</th>
        <th>Dataset</th>
        <th>Model</th>
        <th>Status</th>
        <th>Accuracy</th>
        <th>Created At</th>
        <th>Action</th>
    </tr>

    <% if (jobs.isEmpty()) { %>
        <tr>
            <td colspan="8">No training jobs found.</td>
        </tr>
    <% } else { 
        for (TrainingJob job : jobs) {
            User u = userDAO.getUserById(job.getCreatedBy());
            String username = (u != null) ? u.getName() : "Unknown";
    %>

    <tr>
        <td><%= job.getId() %></td>
        <td><%= username %></td>
        <td><%= job.getDatasetId() %></td>
        <td><%= job.getModelName() %></td>
        <td><%= job.getStatus() %></td>
        <td><%= job.getAccuracy() %> %</td>
        <td><%= job.getCreatedAt() %></td>

        <td>
            <a class="btn-del" href="delete-training?id=<%= job.getId() %>">
                Delete
            </a>
        </td>
    </tr>

    <%  } } %>

</table>

<br><br>
<a href="admin-dashboard.jsp">← Back</a>

</body>
</html>
