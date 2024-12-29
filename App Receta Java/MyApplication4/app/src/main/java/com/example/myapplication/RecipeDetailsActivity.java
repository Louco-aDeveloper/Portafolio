package com.example.myapplication;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        // Obtener los datos pasados desde SavedRecipesActivity
        String recipeName = getIntent().getStringExtra("recipeName");
        String ingredients = getIntent().getStringExtra("ingredients");
        String preparation = getIntent().getStringExtra("preparation");
        String cookingTime = getIntent().getStringExtra("cookingTime");
        float rating = getIntent().getFloatExtra("rating", 0);

        // Mostrar en TextViews correspondientes
        TextView recipeNameTextView = findViewById(R.id.receta_seleccionada);
        TextView ingredientsTextView = findViewById(R.id.ingredientes_detalles);
        TextView preparationTextView = findViewById(R.id.preparacion_detalles);
        TextView cookingTimeTextView = findViewById(R.id.tiempo_coccion_detalles);
        RatingBar ratingBar = findViewById(R.id.valoracion_rating);

        recipeNameTextView.setText(recipeName);
        ingredientsTextView.setText(ingredients);
        preparationTextView.setText(preparation);
        cookingTimeTextView.setText(cookingTime);
        ratingBar.setRating(rating);
    }
}
