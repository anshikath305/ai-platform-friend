<!DOCTYPE html>
<html>
<head>
    <title>Create Experiment</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body { font-family: Arial; padding: 40px; }
        input, textarea {
            width: 300px; padding: 10px; margin: 10px 0; display: block;
        }
        button {
            padding: 10px 20px;
            background: #2563eb;
            color: white;
            border-radius: 5px;
            border: none;
        }
    </style>
</head>
<body>

<h2>Create New Experiment</h2>

<form action="create-experiment" method="POST">
    <label>Title</label>
    <input type="text" name="title" required>

    <label>Description</label>
    <textarea name="description" rows="5"></textarea>

    <button type="submit">Create</button>
</form>

</body>
</html>
