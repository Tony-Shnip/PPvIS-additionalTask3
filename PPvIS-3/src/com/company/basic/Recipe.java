package com.company.basic;

public class Recipe {
    private final String name;
    private final String[] ingredients;
    private final String mealType;
    private String recipeText;
    private String[] appliance;

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

    public String[] getAppliance() {
        return this.appliance;
    }

    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }

    public void setAppliance(String[] appliance) {
        this.appliance = appliance;
    }

    public Recipe(String name, String[] ingredients, String mealType) {
        this.name = name;
        this.ingredients = ingredients;
        this.mealType = mealType;
    }
}
