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
    <title>Your Profile</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<h2>Your Profile</h2>

<div class="card">
    <p><b>Name:</b> <%= user.getName() %></p>
    <p><b>Email:</b> <%= user.getEmail() %></p>
    <p><b>Role:</b> <%= user.getRole() %></p>
</div>

<a href="researcher-dashboard.jsp" class="btn">← Back</a>

</body>
</html>
