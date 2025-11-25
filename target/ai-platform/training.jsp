<%@ page import="java.util.List" %>
<%@ page import="com.ai.platform.dao.DatasetDAO" %>
<%@ page import="com.ai.platform.model.Dataset" %>
<%@ page import="com.ai.platform.model.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    DatasetDAO dao = new DatasetDAO();
    List<Dataset> datasets = dao.getDatasetsByUser(user.getId());
%>

<!DOCTYPE html>
<html>
<head>
    <title>Start Model Training</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body { font-family: Arial; padding: 20px; }
        select, textarea, input { width: 300px; padding: 10px; margin: 10px 0; }
        button {
            padding: 10px 20px;
            background-color: #2563eb;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 6px;
        }
        button:hover { background-color: #1e40af; }
    </style>
</head>
<body>

<h1>Start New Model Training</h1>

<form action="train-model" method="post">

    <label>Select Dataset:</label><br>
    <select name="dataset_id" required>
        <option value="">-- Select Dataset --</option>
        <% for (Dataset d : datasets) { %>
            <option value="<%= d.getId() %>"><%= d.getName() %></option>
        <% } %>
    </select>
    <br><br>

    <label>Select Model:</label><br>
    <select name="model_name" required>
        <option value="CNN">CNN</option>
        <option value="RNN">RNN</option>
        <option value="Transformer">Transformer</option>
        <option value="DecisionTree">Decision Tree</option>
    </select>
    <br><br>

    <label>Training Parameters (JSON recommended):</label><br>
    <textarea name="parameters" rows="4" placeholder='{"epochs":10, "lr":0.001}'></textarea>
    <br><br>

    <button type="submit">Start Training</button>
</form>

</body>
</html>
