<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>AI Platform - Login</title>
    <link rel="stylesheet" href="assets/css/style.css">

</head>
<body>

<h2>Login</h2>

<% if (request.getParameter("error") != null) { %>
    <p style="color:red;">Invalid email or password!</p>
<% } %>

<form action="login" method="post">
    Email: <input type="email" name="email" required><br><br>
    Password: <input type="password" name="password" required><br><br>

    <button type="submit">Login</button>
</form>
<p>Don't have an account? <a href="signup.jsp">Create one</a></p>

</body>
</html>
