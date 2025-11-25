<%@ page import="com.ai.platform.model.User" %>

<%
    User admin = (User) session.getAttribute("user");
    if (admin == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
        }

        .sidebar {
            width: 250px;
            height: 100vh;
            background-color: #1f2937;
            color: white;
            padding: 20px;
        }

        .sidebar h2 {
            margin-bottom: 30px;
        }

        .sidebar a {
            display: block;
            text-decoration: none;
            color: white;
            margin: 12px 0;
            padding: 8px 5px;
        }

        .sidebar a:hover {
            background-color: #374151;
        }

        .content {
            flex: 1;
            padding: 40px;
        }

        .card-container {
            display: flex;
            gap: 25px;
            margin-top: 20px;
        }

        .card {
            width: 260px;
            padding: 20px;
            background-color: #f3f4f6;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .card h3 {
            margin-top: 0;
        }

        .logout {
            margin-top: 40px;
            color: #f87171;
        }
    </style>
</head>

<body>

<div class="sidebar">
    <h2>Admin Panel – Smart AI Workspace</h2>


    <a href="admin-dashboard.jsp">Dashboard</a>
    <a href="admin-users.jsp">Manage Users</a>
    <a href="admin-datasets.jsp">All Datasets</a>
    <a href="admin-training.jsp">Training Jobs</a>
    <a href="admin-experiments">Experiments</a>
    <a href="admin-datasets.jsp">Dataset Overview</a>


    <a class="logout" href="logout.jsp">Logout</a>
</div>

<div class="content">
    <h1>Welcome, <%= admin.getName() %></h1>
    <p>You are logged in as <b>ADMIN</b>.</p>

    <div class="card-container">

        <div class="card">
            <h3>Total Users</h3>
            <p>Displayed later</p>
        </div>

        <div class="card">
            <h3>Total Datasets</h3>
            <p>Displayed later</p>
        </div>

        <div class="card">
            <h3>Training Jobs</h3>
            <p>Displayed later</p>
        </div>

    </div>

</div>

</body>
</html>

