<?php
$servername = "localhost"; // Dirección del servidor (localhost si es local)
$username = "root"; // El usuario para conectar con la base de datos
$password = "123456"; // La contraseña (deja vacía si no tienes una)
$dbname = "multi"; // Nombre de la base de datos que creaste


// Crear la conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Comprobar la conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}
?>
