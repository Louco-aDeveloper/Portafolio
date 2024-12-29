<?php
include('db.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST['email'];

    // Verificar si el correo existe
    $stmt = $pdo->prepare("SELECT * FROM users WHERE email = :email");
    $stmt->execute(['email' => $email]);
    $user = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($user) {
        // Enviar enlace de recuperación (puedes implementar un sistema de recuperación aquí)
        echo "Enlace de recuperación enviado a " . $email;
    } else {
        echo "Correo no registrado.";
    }
}
?>
