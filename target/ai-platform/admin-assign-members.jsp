<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.dao.UserDAO" %>
<%@ page import="com.ai.platform.dao.ProjectMemberDAO" %>

<%
    int projectId = Integer.parseInt(request.getParameter("project_id"));

    UserDAO userDao = new UserDAO();
    List<com.ai.platform.model.User> researchers = userDao.getAllResearchers();

    ProjectMemberDAO pmDao = new ProjectMemberDAO();
    List<Integer> existingMembers = pmDao.getMembersByProject(projectId);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Assign Members</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body { font-family: Arial; padding: 40px; }
        button { padding: 7px 12px; background: #2563eb; color: white; border: none; border-radius: 5px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border-bottom: 1px solid #ccc; }
    </style>
</head>
<body>

<h2>Assign Researchers to Project #<%= projectId %></h2>

<table>
    <tr>
        <th>User</th>
        <th>Email</th>
        <th>Action</th>
    </tr>

    <% for (com.ai.platform.model.User r : researchers) { %>
    <tr>
        <td><%= r.getName() %></td>
        <td><%= r.getEmail() %></td>
        <td>
            <% if (existingMembers.contains(r.getId())) { %>
                Already Added
            <% } else { %>
                <form action="add-member" method="post" style="margin:0;">
                    <input type="hidden" name="project_id" value="<%= projectId %>">
                    <input type="hidden" name="user_id" value="<%= r.getId() %>">
                    <button type="submit">Add</button>
                </form>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>

</body>
</html>
