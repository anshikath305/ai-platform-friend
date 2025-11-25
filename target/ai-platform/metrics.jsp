<!DOCTYPE html>
<html>
<head>
    <title>Model Metrics</title>
    <link rel="stylesheet" href="assets/css/style.css">

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
        }

        .chart-container {
            width: 600px;
            margin-bottom: 40px;
        }
    </style>
</head>
<body>

<h1>Model Metrics</h1>

<div class="chart-container">
    <h3>Accuracy Across Epochs</h3>
    <canvas id="accuracyChart"></canvas>
</div>

<div class="chart-container">
    <h3>Loss Across Epochs</h3>
    <canvas id="lossChart"></canvas>
</div>

<div class="chart-container">
    <h3>Precision Across Epochs</h3>
    <canvas id="precisionChart"></canvas>
</div>

<div class="chart-container">
    <h3>Recall Across Epochs</h3>
    <canvas id="recallChart"></canvas>
</div>

<script>
    // Load metrics from servlet
    fetch("metrics-data")
        .then(response => response.json())
        .then(data => {
            const epochs = ["Epoch 1", "Epoch 2", "Epoch 3", "Epoch 4", "Epoch 5"];

            function makeChart(id, label, values, color) {
                new Chart(document.getElementById(id), {
                    type: "line",
                    data: {
                        labels: epochs,
                        datasets: [{
                            label: label,
                            data: values,
                            borderColor: color,
                            borderWidth: 2,
                            tension: 0.3
                        }]
                    }
                });
            }

            makeChart("accuracyChart",  "Accuracy",  data.accuracy,  "green");
            makeChart("lossChart",      "Loss",      data.loss,      "red");
            makeChart("precisionChart", "Precision", data.precision, "blue");
            makeChart("recallChart",    "Recall",    data.recall,    "orange");
        });
</script>

</body>
</html>
