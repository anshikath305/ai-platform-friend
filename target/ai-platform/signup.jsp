<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>AI Platform - Signup</title>
    <link rel="stylesheet" href="assets/css/style.css">

</head>
<body>

<h2>Create a New Account</h2>

<form action="signup" method="post">
    Name: <input type="text" name="name" required><br><br>

    Email: <input type="email" name="email" required><br><br>

    Password: <input type="password" name="password" required><br><br>

    Role:
    <select name="role">
        <option value="RESEARCHER">Researcher</option>
        <option value="ADMIN">Admin</option>
    </select>
    <br><br>

    <button type="submit">Create Account</button>
</form>

</body>
</html>
