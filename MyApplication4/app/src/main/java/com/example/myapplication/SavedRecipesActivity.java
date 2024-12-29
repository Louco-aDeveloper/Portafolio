package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SavedRecipesActivity extends AppCompatActivity {

    private ListView listView;
    private EditText searchEditText;
    private ArrayList<String> recipeList;
    private ArrayList<String> filteredRecipeList;
    private ArrayAdapter<String> adapter;
    private FirebaseFirestore db;
    private CollectionReference recipesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipes);

        // Inicializa Firestore
        db = FirebaseFirestore.getInstance();
        recipesRef = db.collection("recetas"); // Asegúrate de que esta sea la colección correcta

        // Inicializar ListView y otros elementos
        listView = findViewById(R.id.listView);
        searchEditText = findViewById(R.id.searchEditText);

        // Inicializar lista de recetas
        recipeList = new ArrayList<>();
        filteredRecipeList = new ArrayList<>();

        // Configurar el adaptador para el ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredRecipeList);
        listView.setAdapter(adapter);

        // Cargar las recetas desde Firestore
        loadRecipes();

        // Configurar el comportamiento de la selección de recetas
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedRecipe = filteredRecipeList.get(position);
            // Aquí puedes agregar lógica para editar o eliminar la receta seleccionada
            showOptionsDialog(selectedRecipe);
        });

        // Agregar funcionalidad de búsqueda
        searchEditText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterRecipes(charSequence.toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable editable) {}
        });
    }

    // Función para cargar las recetas desde Firestore
    private void loadRecipes() {
        recipesRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            recipeList.clear();
                            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                // Extraer los campos de cada receta
                                String category = document.getString("categoría");
                                String ingredients = document.getString("ingredientes");
                                String recipeName = document.getString("nombre");
                                String preparation = document.getString("preparacion");
                                String cookingTime = document.getString("tiempoCoccion");

                                // Verificar si el campo nombre no es nulo
                                if (recipeName != null) {
                                    String recipeDetails = "Receta: " + recipeName + "\n" +
                                            "Categoría: " + (category != null ? category : "Desconocida") + "\n" +
                                            "Ingredientes: " + (ingredients != null ? ingredients : "Desconocido") + "\n" +
                                            "Preparación: " + (preparation != null ? preparation : "Desconocida") + "\n" +
                                            "Tiempo de cocción: " + (cookingTime != null ? cookingTime : "Desconocido");

                                    recipeList.add(recipeDetails);
                                }
                            }
                            // Inicializar la lista filtrada con todas las recetas al inicio
                            filteredRecipeList.addAll(recipeList);
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(SavedRecipesActivity.this, "No hay recetas guardadas", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SavedRecipesActivity.this, "Error al cargar las recetas", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Función para filtrar las recetas según lo que se escriba en el campo de búsqueda
    private void filterRecipes(String query) {
        filteredRecipeList.clear();
        for (String recipe : recipeList) {
            if (recipe.toLowerCase().contains(query.toLowerCase())) {
                filteredRecipeList.add(recipe);
            }
        }
        adapter.notifyDataSetChanged();
    }

    // Función para mostrar el diálogo de opciones de edición y eliminación
    private void showOptionsDialog(String selectedRecipe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionaste la receta: " + selectedRecipe.split("\n")[0]);

        builder.setItems(new CharSequence[]{"Eliminar", "Editar"}, (dialog, which) -> {
            if (which == 0) {
                // Eliminar receta
                deleteRecipe(selectedRecipe);
            } else if (which == 1) {
                // Editar receta (abrir una nueva actividad con un formulario de edición)
                editRecipe(selectedRecipe);
            }
        });

        builder.show();
    }

    // Función para eliminar la receta seleccionada de Firestore
    private void deleteRecipe(String selectedRecipe) {
        // Obtener el nombre de la receta para eliminarla de Firestore
        String recipeName = selectedRecipe.split("\n")[0].replace("Receta: ", "");

        // Buscar el documento de la receta por nombre
        recipesRef.whereEqualTo("nombre", recipeName).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (!querySnapshot.isEmpty()) {
                            String documentId = querySnapshot.getDocuments().get(0).getId();
                            recipesRef.document(documentId).delete()
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(SavedRecipesActivity.this, "Receta eliminada", Toast.LENGTH_SHORT).show();
                                        loadRecipes(); // Recargar las recetas para actualizar la lista
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(SavedRecipesActivity.this, "Error al eliminar la receta", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    }
                });
    }

    // Función para editar la receta (abre una nueva actividad con el formulario de edición)
    private void editRecipe(String selectedRecipe) {
        // Obtener el nombre de la receta seleccionada
        String recipeName = selectedRecipe.split("\n")[0].replace("Receta: ", "");
        Toast.makeText(SavedRecipesActivity.this, "Editando receta: " + recipeName, Toast.LENGTH_SHORT).show();

        // Crear un Intent para abrir la actividad EditRecipeActivity
        Intent intent = new Intent(SavedRecipesActivity.this, EditRecipeActivity.class);

        // Pasar el nombre de la receta seleccionada como un Extra al Intent
        intent.putExtra("recipe_name", recipeName);

        // Iniciar la actividad de edición
        startActivity(intent);
    }
}
