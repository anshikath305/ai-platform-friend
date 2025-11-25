<%@ page import="com.ai.platform.model.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String projectId = request.getParameter("project_id");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Member</title>

    <!-- ✅ MOVE CSS HERE -->
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body { font-family: Arial; padding: 30px; }
        input, button { padding: 10px; margin-top: 10px; width: 300px; }
    </style>
</head>
<body>

<h2>Add Member to Project</h2>

<form action="add-member" method="post">
    <input type="hidden" name="project_id" value="<%= projectId %>">

    <label>Enter Member Email:</label><br>
    <input type="email" name="email" placeholder="researcher@example.com" required><br>

    <button type="submit">Add Member</button>
</form>

<% if (request.getParameter("success") != null) { %>
    <p style="color: green;">Member added successfully!</p>
<% } %>

<% if (request.getParameter("error") != null) { %>
    <p style="color: red;">Failed to add member. User not found or already added.</p>
<% } %>

</body>
</html>
