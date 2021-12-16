package com.company;

import com.company.allergy.AllergiesBase;
import com.company.basic.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        RecipesBase recipesBase = new RecipesBase();
        ArrayList<Recipe> recipes = recipesBase.getRecipes();
//        System.out.println(recipes.get(0).getIngredients()[0]);
        FoodstuffBase foodstuffBase = new FoodstuffBase();
        ArrayList<String> foodstuff = foodstuffBase.getAllFoodstuff();
//        System.out.println(foodstuff.toArray().length);
        AllergiesBase allergiesBase = new AllergiesBase();
        ArrayList<String> allergies = allergiesBase.getAllergies();
        Logic logic = new Logic(recipesBase, foodstuffBase ,recipes, foodstuff, allergies);
        logic.Start();
    }
}
