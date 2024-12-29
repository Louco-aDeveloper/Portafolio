package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> recipes;

    public RecipeAdapter(Context context, List<String> recipes) {
        super(context, 0, recipes);
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Si la vista no está creada (convertView es null), inflamos el diseño del ítem
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_recipe, parent, false);
        }

        // Obtener la receta actual (puedes modificar la forma en que las recetas se almacenan)
        String recipe = recipes.get(position);

        // Obtener las vistas dentro del ítem
        TextView recipeNameTextView = convertView.findViewById(R.id.recipeName);
        TextView recipeSummaryTextView = convertView.findViewById(R.id.recipeSummary);
        TextView cookingTimeTextView = convertView.findViewById(R.id.recipeCookingTime);

        // Dividir los detalles de la receta (asumiendo que cada receta tiene este formato)
        String[] recipeDetails = recipe.split("\n");

        // Asignar los valores a las vistas
        recipeNameTextView.setText("Receta " + (position + 1)); // Nombre de la receta
        recipeSummaryTextView.setText(recipeDetails[0].replace("Ingredientes: ", "")); // Ingredientes
        cookingTimeTextView.setText(recipeDetails[2].replace("Tiempo de Cocción: ", "")); // Tiempo de cocción

        return convertView;
    }
}
