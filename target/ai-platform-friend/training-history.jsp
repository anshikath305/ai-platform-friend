<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.dao.TrainingJobDAO" %>
<%@ page import="com.ai.platform.model.TrainingJob" %>
<%@ page import="com.ai.platform.model.User" %>

<%
    // Validate user session
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch user's training jobs
    TrainingJobDAO dao = new TrainingJobDAO();
    List<TrainingJob> jobs = dao.getTrainingJobs(user.getId());
%>

<!DOCTYPE html>
<html>
<head>
    <title>Training History</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <meta http-equiv="refresh" content="3">

    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #1f2937;
            color: white;
        }

        .status {
            padding: 5px 10px;
            border-radius: 5px;
            color: white;
            font-weight: bold;
        }

        .pending { background-color: #9ca3af; }
        .running { background-color: #3b82f6; }
        .completed { background-color: #16a34a; }
        .failed { background-color: #dc2626; }

        /* Progress bar design */
        .bar-bg {
            width: 150px;
            height: 12px;
            background-color: #e5e7eb;
            border-radius: 6px;
            margin-top: 6px;
        }

        .bar-fill {
            height: 12px;
            background-color: #2563eb;
            border-radius: 6px;
        }

        /* Dynamic widths */
        .progress-0 { width: 0%; }
        .progress-10 { width: 10%; }
        .progress-20 { width: 20%; }
        .progress-30 { width: 30%; }
        .progress-40 { width: 40%; }
        .progress-50 { width: 50%; }
        .progress-60 { width: 60%; }
        .progress-70 { width: 70%; }
        .progress-80 { width: 80%; }
        .progress-90 { width: 90%; }
        .progress-100 { width: 100%; }
    </style>
</head>

<body>

<h1>Training History</h1>
<p>(Refreshing every 3 seconds…)</p>

<table>
    <tr>
        <th>ID</th>
        <th>Dataset</th>
        <th>Model</th>
        <th>Status</th>
        <th>Accuracy</th>
        <th>Started</th>
    </tr>

    <% 
        if (jobs != null && !jobs.isEmpty()) {
            for (TrainingJob job : jobs) { 
    %>

    <tr>
        <td><%= job.getId() %></td>
        <td><%= job.getDatasetId() %></td>
        <td><%= job.getModelName() %></td>

        <td>
            <span class="status <%= job.getStatus().toLowerCase() %>">
                <%= job.getStatus() %>
            </span>
        </td>

        <td>
            <%
                int acc = (int) job.getAccuracy();
                if (acc < 0) acc = 0;
                if (acc > 100) acc = 100;
            %>

            <%= acc %>%  
            <div class="bar-bg">
                <div class="bar-fill progress-<%= acc %>"></div>
            </div>
        </td>

        <td><%= job.getCreatedAt() %></td>
    </tr>

    <% 
            }
        } else { 
    %>

    <tr>
        <td colspan="6" style="text-align: center; color: #666;">
            No training jobs found.
        </td>
    </tr>

    <% } %>

</table>

</body>
</html>

