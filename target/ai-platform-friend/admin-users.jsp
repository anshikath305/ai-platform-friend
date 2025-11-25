<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.dao.UserDAO" %>
<%@ page import="com.ai.platform.model.User" %>

<%
    User admin = (User) session.getAttribute("user");
    if (admin == null || !"ADMIN".equals(admin.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    UserDAO dao = new UserDAO();
    List<User> users = dao.getAllUsers();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin – Users</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body { font-family: Arial; padding: 20px; }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #1f2937;
            color: white;
        }

        .btn-delete {
            background: #dc2626;
            padding: 6px 10px;
            color: white;
            border-radius: 5px;
            text-decoration: none;
        }

        .btn-delete:hover {
            background: #b91c1c;
        }

        .role-select {
            padding: 5px;
        }
    </style>
</head>
<body>

<h2>User Management</h2>
<p>View, modify roles, or delete users.</p>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Update Role</th>
        <th>Delete</th>
    </tr>

    <% for (User u : users) { %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getName() %></td>
            <td><%= u.getEmail() %></td>

            <td><%= u.getRole() %></td>

            <td>
                <form action="update-role" method="post">
                    <input type="hidden" name="user_id" value="<%= u.getId() %>">

                    <select name="role" class="role-select">
                        <option value="researcher" <%= "researcher".equals(u.getRole()) ? "selected" : "" %>>Researcher</option>
                        <option value="admin" <%= "admin".equals(u.getRole()) ? "selected" : "" %>>Admin</option>
                    </select>

                    <button type="submit">Update</button>
                </form>
            </td>

            <td>
                <a href="delete-user?id=<%= u.getId() %>"
                   class="btn-delete"
                   onclick="return confirm('Delete this user?');">
                    Delete
                </a>
            </td>
        </tr>
    <% } %>
</table>

</body>
</html>
