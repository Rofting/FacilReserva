<?php
$url = "http://localhost:8080/restaurants";
$response = @file_get_contents($url);

if ($response === false) {
    die("<div class='alert alert-danger text-center'>Error: No se pudo obtener la información de la API.</div>");
}

$restaurants = json_decode($response, true);
if ($restaurants === null) {
    die("<div class='alert alert-warning text-center'>Error: Datos inválidos recibidos de la API.</div>");
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurants - FacilReserva</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="/">FacilReserva</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/restaurants.php">Restaurants</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/reservations.php">Reservations</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="mb-4">List of Restaurants</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Location</th>
            <th>Capacity</th>
        </tr>
        </thead>
        <tbody>
        <?php if (!empty($restaurants)): ?>
            <?php foreach ($restaurants as $restaurant): ?>
                <tr>
                    <td><?= htmlspecialchars($restaurant['id']) ?></td>
                    <td><?= htmlspecialchars($restaurant['name']) ?></td>
                    <td><?= htmlspecialchars($restaurant['address']) ?></td>
                    <td><?= htmlspecialchars($restaurant['capacity']) ?></td>
                </tr>
            <?php endforeach; ?>
        <?php else: ?>
            <tr>
                <td colspan="4" class="text-center">No restaurants found.</td>
            </tr>
        <?php endif; ?>
        </tbody>
    </table>
</div>

<footer class="bg-light text-center text-lg-start mt-5">
    <div class="container p-3">
        <p class="mb-0">&copy; 2025 FacilReserva. All rights reserved.</p>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

