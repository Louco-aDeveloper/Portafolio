package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    // Instancia de FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome); // Asegúrate de que este layout exista

        // Inicializa FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Encuentra los botones
        Button btnSignOut = findViewById(R.id.btnLogout); // Botón para cerrar sesión
        Button btnAddRecipe = findViewById(R.id.btnAddRecipe); // Botón para agregar recetas

        // Cerrar sesión
        btnSignOut.setOnClickListener(view -> {
            mAuth.signOut();  // Cierra la sesión
            Toast.makeText(WelcomeActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();

            // Redirige al usuario a MainActivity (Inicio de sesión)
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  // Limpiar la pila de actividades
            startActivity(intent);
            finish(); // Termina la actividad actual para evitar regresar a esta al presionar "Atrás"
        });

        // Agregar receta (redirige a AddRecipeActivity)
        btnAddRecipe.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomeActivity.this, AddRecipeActivity.class);
            startActivity(intent);
        });
    }

    // Cierra sesión automáticamente si el usuario no está autenticado
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            // Si no hay usuario logueado, redirige al login (MainActivity)
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finaliza la actividad actual
        }
    }
}
