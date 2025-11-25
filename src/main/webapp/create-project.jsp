<%@ page import="com.ai.platform.model.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Create Project</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
            max-width: 650px;
            margin: auto;
        }
        h2 { color: #1f2937; }
        label { font-weight: bold; display: block; margin-top: 15px; }
        input, textarea {
            width: 100%;
            padding: 12px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }
        button {
            margin-top: 20px;
            padding: 12px 20px;
            background-color: #2563eb;
            border: none;
            color: white;
            border-radius: 6px;
            cursor: pointer;
        }
        button:hover {
            background-color: #1d4ed8;
        }
    </style>
</head>
<body>

<h2>Create New Collaboration Project</h2>

<form action="create-project" method="post">

    <label>Project Title</label>
    <input type="text" name="title" required>

    <label>Description</label>
    <textarea name="description" rows="5"></textarea>

    <button type="submit">Create Project</button>
</form>

</body>
</html>
