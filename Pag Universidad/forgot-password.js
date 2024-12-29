document.getElementById("forgot-password-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value.trim();

    if (!email) {
        alert("Por favor, introduce tu correo electrónico.");
        return;
    }

    alert("Se ha enviado un enlace de recuperación a: " + email);
});
