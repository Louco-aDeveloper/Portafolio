package com.example.myapplication;

public class Recipe {
    private final String name;        // Nombre de la receta
    private final String ingredients; // Ingredientes
    private final String preparation; // Preparación
    private final String cookingTime; // Tiempo de cocción
    private final float rating;       // Calificación (puede ser de 1 a 5, por ejemplo)

    // Constructor
    public Recipe(String name, String ingredients, String preparation, String cookingTime, float rating) {
        this.name = name;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.cookingTime = cookingTime;
        this.rating = rating;
    }

    // Métodos para obtener la información de la receta
    public String getName() { return name; }
    public String getIngredients() { return ingredients; }
    public String getPreparation() { return preparation; }
    public String getCookingTime() { return cookingTime; }
    public float getRating() { return rating; }
}
