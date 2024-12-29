package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddRecipeActivity extends AppCompatActivity {
    private Spinner recetasDestacadasSpinner;
    private Spinner categoriaSpinner;
    private EditText ingredientesEditText;
    private EditText preparacionEditText;
    private EditText coccionEditText;
    private Button btnAddRecipe;
    private Button btnViewSavedRecipes;

    private FirebaseFirestore db; // Instancia de Firebase Firestore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        // Inicializar las vistas
        recetasDestacadasSpinner = findViewById(R.id.recetas_destacadas_spinner);
        categoriaSpinner = findViewById(R.id.categoria_spinner);
        ingredientesEditText = findViewById(R.id.ingredientesEditText);
        preparacionEditText = findViewById(R.id.preparacionEditText);
        coccionEditText = findViewById(R.id.coccionEditText);
        btnAddRecipe = findViewById(R.id.btnAddRecipe);
        btnViewSavedRecipes = findViewById(R.id.btnViewSavedRecipes);

        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Lista de recetas destacadas
        String[] recetas = {"Empanadas", "Pizza", "Tacos", "Sushi"};
        ArrayAdapter<String> recetasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, recetas);
        recetasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recetasDestacadasSpinner.setAdapter(recetasAdapter);

        // Lista de categorías
        String[] categorias = {"Entrantes", "Platos Principales", "Postres"};
        ArrayAdapter<String> categoriasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        categoriasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaSpinner.setAdapter(categoriasAdapter);

        // Configurar el botón para agregar receta
        btnAddRecipe.setOnClickListener(view -> {
            // Obtener los valores ingresados por el usuario
            String recetaSeleccionada = recetasDestacadasSpinner.getSelectedItem().toString();
            String categoriaSeleccionada = categoriaSpinner.getSelectedItem().toString();
            String ingredientes = ingredientesEditText.getText().toString().trim();
            String preparacion = preparacionEditText.getText().toString().trim();
            String tiempoCoccion = coccionEditText.getText().toString().trim();

            // Validación de campos
            if (ingredientes.isEmpty() || preparacion.isEmpty() || tiempoCoccion.isEmpty()) {
                Toast.makeText(AddRecipeActivity.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear un mapa con los datos de la receta
            Map<String, Object> receta = new HashMap<>();
            receta.put("nombre", recetaSeleccionada);
            receta.put("categoria", categoriaSeleccionada);
            receta.put("ingredientes", ingredientes);
            receta.put("preparacion", preparacion);
            receta.put("tiempoCoccion", tiempoCoccion);

            // Deshabilitar el botón mientras se guarda la receta
            btnAddRecipe.setEnabled(false);

            // Agregar la receta a Firestore
            db.collection("recetas")
                    .add(receta)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(AddRecipeActivity.this, "Receta " + recetaSeleccionada + " guardada", Toast.LENGTH_SHORT).show();
                        // Limpiar campos después de agregar la receta
                        ingredientesEditText.setText("");
                        preparacionEditText.setText("");
                        coccionEditText.setText("");
                        recetasDestacadasSpinner.setSelection(0); // Resetear spinner
                        categoriaSpinner.setSelection(0); // Resetear spinner
                        btnAddRecipe.setEnabled(true); // Habilitar el botón nuevamente
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(AddRecipeActivity.this, "Error al guardar la receta: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        btnAddRecipe.setEnabled(true); // Habilitar el botón nuevamente
                    });
        });

        // Configurar el botón para ver recetas guardadas
        btnViewSavedRecipes.setOnClickListener(view -> {
            Intent intent = new Intent(AddRecipeActivity.this, SavedRecipesActivity.class);
            startActivity(intent);
        });
    }
}
