package com.company.basic;

public class Recipe {
    private String name;
    private String[] ingredients;
    private String mealType;
    private String recipeText;

    public String getName() {
        return this.name;
    }

    public String[] getIngredients() {
        return this.ingredients;
    }

    public String getMealType() {
        return this.mealType;
    }

    public String getRecipeText() {
        return this.recipeText;
    }

    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }

    public Recipe(String name, String[] ingredients, String mealType) {
        this.name = name;
        this.ingredients = ingredients;
        this.mealType = mealType;
    }
}
