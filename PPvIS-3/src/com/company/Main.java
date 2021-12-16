package com.company;

import com.company.allergy.AllergiesBase;
import com.company.appliance.AppliancesBase;
import com.company.basic.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        RecipesBase recipesBase = new RecipesBase();
        ArrayList<Recipe> recipes = recipesBase.getRecipes();

        FoodstuffBase foodstuffBase = new FoodstuffBase();
        ArrayList<String> foodstuff = foodstuffBase.getAllFoodstuff();

        AllergiesBase allergiesBase = new AllergiesBase();
        ArrayList<String> allergies = allergiesBase.getAllergies();

        AppliancesBase appliancesBase = new AppliancesBase();
        ArrayList<String> appliances = appliancesBase.getAppliances();

        Logic logic = new Logic(recipesBase, foodstuffBase, appliancesBase ,recipes, foodstuff, allergies, appliances);
        logic.Start();
    }
}
