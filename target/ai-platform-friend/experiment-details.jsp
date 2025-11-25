<%@ page import="com.ai.platform.dao.ExperimentDAO" %>
<%@ page import="com.ai.platform.model.Experiment" %>
<%@ page import="com.ai.platform.model.User" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int experimentId = Integer.parseInt(request.getParameter("id"));

    ExperimentDAO dao = new ExperimentDAO();
    Experiment experiment = dao.getExperimentById(experimentId);

    if (experiment == null) {
        out.println("<h2>Experiment not found</h2>");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Experiment Details</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
        }

        .box {
            padding: 15px;
            margin-bottom: 25px;
            border-radius: 10px;
            background-color: #f3f4f6;
        }

        pre {
            background: #111827;
            color: #10b981;
            padding: 20px;
            border-radius: 8px;
            height: 300px;
            overflow-y: scroll;
            font-size: 14px;
        }
    </style>
</head>
<body>

<h1>Experiment: <%= experiment.getName() %></h1>

<div class="box">
    <p><b>Dataset ID:</b> <%= experiment.getDatasetId() %></p>
    <p><b>Status:</b> <%= experiment.getStatus() %></p>
    <p><b>Created:</b> <%= experiment.getCreatedAt() %></p>
</div>

<div class="box">
    <h2>Logs (Real-Time)</h2>
    <pre id="logBox">Loading logs…</pre>
</div>

<script>
function loadLogs() {
    fetch("get-experiment-logs?id=<%= experiment.getId() %>")
        .then(response => response.json())
        .then(data => {
            let output = "";
            data.forEach(log => {
                output += "[" + log.createdAt + "] " + log.logText + "\n";
            });
            document.getElementById("logBox").textContent = output;
        });
}

// refresh every 2 seconds
setInterval(loadLogs, 2000);
loadLogs();
</script>

</body>
</html>
