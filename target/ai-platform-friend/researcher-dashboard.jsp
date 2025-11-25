<%@ page import="com.ai.platform.model.User" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    // Get logged-in user from session
    HttpSession sessionObj = request.getSession(false);
    User user = (User) sessionObj.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Researcher Dashboard</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            display: flex;
        }

        .sidebar {
            width: 250px;
            background-color: #1f2937;
            color: white;
            height: 100vh;
            padding: 20px;
        }

        .sidebar h2 {
            margin-bottom: 25px;
        }

        .sidebar a {
            display: block;
            color: white;
            text-decoration: none;
            margin: 12px 0;
            font-size: 16px;
        }

        .sidebar a:hover {
            color: #9ca3af;
        }

        .content {
            flex: 1;
            padding: 30px;
        }

        .logout-btn {
            margin-top: 30px;
            color: #f87171;
        }

        .action-buttons {
            margin-top: 30px;
        }

        .action-buttons button {
            padding: 10px 20px;
            margin-right: 15px;
            background-color: #2563eb;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .action-buttons button:hover {
            background-color: #1e40af;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h2>Welcome to Smart AI Workspace</h2>


    <a href="dataset.jsp">Dataset Management</a>
    <a href="training.jsp">Model Training</a>
    <a href="training-history.jsp">Training History</a>
    <a href="experiments.jsp">Experiment Tracking</a>
    <a href="metrics.jsp">Model Metrics</a>
    <a href="collaboration.jsp">Collaboration</a>
    <a href="profile.jsp">Profile</a>

    <a class="logout-btn" href="logout.jsp">Logout</a>
</div>

<div class="content">
    <h1>Welcome, <%= user.getName() %>!</h1>
    <p>You are logged in as a <b><%= user.getRole() %></b>.</p>

    <h3>Your Research Tools</h3>
    <p>Select a module from the left to begin your work.</p>

    <div class="action-buttons">
        <a href="training.jsp"><button>Start New Training</button></a>
        <a href="training-history.jsp"><button>Training History</button></a>
    </div>
</div>

</body>
</html>
