<%@ page import="com.ai.platform.model.User" %>

<%
    User admin = (User) session.getAttribute("user");
    if (admin == null || !admin.getRole().equals("ADMIN")) {
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
        body { font-family: Arial; padding: 40px; }
        input, textarea { width: 100%; padding: 10px; margin: 10px 0; }
        button { padding: 10px 18px; background: #2563eb; color: white; border: none; border-radius: 5px; }
    </style>
</head>
<body>

<h2>Create a New Project</h2>

<form action="create-project" method="post">
    <label>Project Title:</label>
    <input type="text" name="title" required>

    <label>Description:</label>
    <textarea name="description" rows="4"></textarea>

    <button type="submit">Create</button>
</form>

</body>
</html>
