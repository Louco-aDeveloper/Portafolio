package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Referencias a los campos
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        registerButton = findViewById(R.id.registerButton);

        // Manejar clic en el botón de registro
        registerButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            // Validar campos
            if (TextUtils.isEmpty(email)) {
                emailEditText.setError("Por favor ingresa un correo electrónico");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                passwordEditText.setError("Por favor ingresa una contraseña");
                return;
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                confirmPasswordEditText.setError("Por favor confirma la contraseña");
                return;
            }

            if (!password.equals(confirmPassword)) {
                confirmPasswordEditText.setError("Las contraseñas no coinciden");
                return;
            }

            // Registrar el usuario en Firebase
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Registro exitoso
                            Toast.makeText(RegisterActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                            // Puedes redirigir al usuario a la pantalla principal o a un login, por ejemplo:
                            // startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        } else {
                            // Error en el registro
                            Toast.makeText(RegisterActivity.this, "Error al registrar el usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
