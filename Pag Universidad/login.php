<?php
include('db.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST['email'];
    $password = $_POST['password'];

    $stmt = $pdo->prepare("SELECT * FROM users WHERE email = :email");
    $stmt->execute(['email' => $email]);
    $user = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($user && password_verify($password, $user['password'])) {
        // Iniciar sesión correctamente
        session_start();
        $_SESSION['user'] = $user['id'];
        echo "Bienvenido, " . $user['username'];
        // Redirigir a la página principal o panel
    } else {
        echo "Correo o contraseña incorrectos.";
    }
}
?>
