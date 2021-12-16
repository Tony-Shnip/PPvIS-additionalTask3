package com.company;

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
        Logic logic = new Logic();
        logic.Start(recipesBase, foodstuffBase ,recipes, foodstuff);
    }
}
