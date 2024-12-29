package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class EditRecipeActivity extends AppCompatActivity {

    private EditText nameEditText, categoryEditText, ingredientsEditText, preparationEditText, cookingTimeEditText;
    private Button saveButton;
    private String recipeName;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Obtener el nombre de la receta desde el intent
        recipeName = getIntent().getStringExtra("recipe_name");

        // Inicializar las vistas
        nameEditText = findViewById(R.id.nameEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        ingredientsEditText = findViewById(R.id.ingredientsEditText);
        preparationEditText = findViewById(R.id.preparationEditText);
        cookingTimeEditText = findViewById(R.id.cookingTimeEditText);
        saveButton = findViewById(R.id.saveButton);

        // Cargar los detalles de la receta desde Firestore
        loadRecipeDetails();

        // Guardar los cambios cuando el usuario presione el botón
        saveButton.setOnClickListener(v -> saveRecipe());
    }

    // Función para cargar los detalles de la receta desde Firestore
    private void loadRecipeDetails() {
        db.collection("recetas").whereEqualTo("nombre", recipeName).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!task.getResult().isEmpty()) {
                            String category = task.getResult().getDocuments().get(0).getString("categoría");
                            String ingredients = task.getResult().getDocuments().get(0).getString("ingredientes");
                            String preparation = task.getResult().getDocuments().get(0).getString("preparacion");
                            String cookingTime = task.getResult().getDocuments().get(0).getString("tiempoCoccion");

                            // Mostrar los detalles en los EditText
                            nameEditText.setText(recipeName);
                            categoryEditText.setText(category);
                            ingredientsEditText.setText(ingredients);
                            preparationEditText.setText(preparation);
                            cookingTimeEditText.setText(cookingTime);
                        }
                    }
                });
    }

    // Función para guardar los cambios en Firestore
    private void saveRecipe() {
        String newCategory = categoryEditText.getText().toString();
        String newIngredients = ingredientsEditText.getText().toString();
        String newPreparation = preparationEditText.getText().toString();
        String newCookingTime = cookingTimeEditText.getText().toString();

        // Actualizar los datos de la receta en Firestore
        db.collection("recetas").whereEqualTo("nombre", recipeName).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!task.getResult().isEmpty()) {
                            String documentId = task.getResult().getDocuments().get(0).getId();
                            db.collection("recetas").document(documentId)
                                    .update(
                                            "categoría", newCategory,
                                            "ingredientes", newIngredients,
                                            "preparacion", newPreparation,
                                            "tiempoCoccion", newCookingTime
                                    )
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(EditRecipeActivity.this, "Receta actualizada", Toast.LENGTH_SHORT).show();
                                        finish(); // Volver a la actividad anterior
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(EditRecipeActivity.this, "Error al actualizar la receta", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    }
                });
    }
}
