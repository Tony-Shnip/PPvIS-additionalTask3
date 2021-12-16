package com.company.basic;

import com.company.appliance.AppliancesBase;

import java.util.ArrayList;
import java.util.Scanner;

public class Logic {

    private final RecipesBase recipesBase;
    private final FoodstuffBase foodstuffBase;
    private final AppliancesBase appliancesBase;
    private final ArrayList<Recipe> recipes;
    private ArrayList<String> foodstuff;
    private final ArrayList<String> allergies;
    private ArrayList<String> appliances;
    private final ArrayList<String[]> changes;
    private final Scanner in = new Scanner(System.in);


    public void Start() {

        while (true) {
            System.out.println("\nВыберите операцию:");
            System.out.println("1 - доступные рецепты,");
            System.out.println("2 - добавленные ингредиенты,");
            System.out.println("3 - выбрать рецепт по ингредиенту,");
            System.out.println("4 - продолжить готовку,");
            System.out.println("5 - выбрать рецепт из списка,");
            System.out.println("6 - добавить ингредиент,");
            System.out.println("7 - добавить прибор,");
            System.out.println("8 - аллергии,");
            System.out.println("9 - приборы,");
            System.out.println("10 - закончить работу.");
            String input = in.nextLine();

            switch (input) {
                case "1":
                    printRecipes();
                    break;
                case "2":
                    printFoodstuff();
                    break;
                case "3":
                    printRecipeByMeal();
                    break;
                case "4":
                    printLastRecipe();
                    break;
                case "5":
                    printChosenRecipe();
                    break;
                case "6":
                    printAddNewFoodstuff();
                    break;
                case "7":
                    printAddNewAppliance();
                    break;
                case "8":
                    printAllergies();
                    break;
                case "9":
                    printAppliances();
                    break;
                case "10":
                    return;
                default:
                    break;
            }
        }

    }

    private void printRecipes() {
        for (int i = 0; i < this.recipes.size(); i++) {
            System.out.print((i + 1) + " - ");
            System.out.println(this.recipes.get(i).getName() + "(" + this.recipes.get(i).getMealType() + ")");
        }
    }

    private void printFoodstuff() {
        for (String foodstuff : this.foodstuff) {
            System.out.println(foodstuff);
        }
    }

    private void printRecipeByMeal() {
        System.out.println("Выберите тип блюда:");
        System.out.println("1 - Завтрак,");
        System.out.println("2 - Обед,");
        System.out.println("3 - Ужин.");
        String mealTypeId = this.in.nextLine();

        System.out.println("Введите желаемый игредиент:");
        String foodstuffName = this.in.nextLine();

        int[] mealTypeIds = new int[50];
        int mealCount = 0;
        for (int i = 0; i < this.recipes.size(); i++) {
            if ((mealTypeId.equals("1") && this.recipes.get(i).getMealType().equals("Завтрак")) ||
                    (mealTypeId.equals("2") && this.recipes.get(i).getMealType().equals("Обед")) ||
                    (mealTypeId.equals("3") && this.recipes.get(i).getMealType().equals("Ужин"))) {

                for (int j = 0; j < this.recipes.get(i).getIngredients().length; j++) {
                    if (this.recipes.get(i).getIngredients()[j].equals(foodstuffName)) {
                        mealCount++;
                        System.out.print((mealCount) + " - ");
                        System.out.println(this.recipes.get(i).getName());
                        mealTypeIds[mealCount - 1] = i;
                    }
                }
            }
        }
        if (mealCount == 0) {
            System.out.println("Нет подходящего рецепта.");
            return;
        }

        System.out.println("Выберите рецепт:");
        String recipeId = this.in.nextLine();

        this.recipesBase.overwriteLastRecipe(this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]));

        System.out.println(this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getName());
        System.out.println("Ингредиенты:");
        for (int i = 0; i < this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients().length; i++) {
            System.out.print((i + 1) + " - ");
            System.out.println(this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i]);
        }

        System.out.println("Можно заменить:");

        for (int i = 0; i < this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients().length; i++) {
            for (String[] change : this.changes) {
                if (this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i].contains(change[0]) ||
                        this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i].contains(change[1])
                ) {
                    System.out.println(change[0] + "<->" + change[1]);
                }
            }
        }

        System.out.println();

        System.out.println("Рецепт: " + this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getRecipeText());

        StringBuilder appliancesList = new StringBuilder("Рекомендуемые приборы: ---");

        for (int i = 0; i < this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getAppliance().length; i++) {
            appliancesList.append(this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getAppliance()[i]).append("---");
        }
        System.out.println(appliancesList);

        StringBuilder noAppliancesList = new StringBuilder("Недостающие приборы: ---");

        for (int i = 0; i < this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getAppliance().length; i++) {
            if (!this.appliances.contains(this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getAppliance()[i])) {
                noAppliancesList.append(this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getAppliance()[i]).append("---");
            }
        }
        System.out.println(noAppliancesList);

        StringBuilder noIngredientsList = new StringBuilder("Недостающие ингредиенты: ---");

        for (int i = 0; i < this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients().length; i++) {
            if (!this.foodstuff.contains(this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i])) {
                noIngredientsList.append(this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i]).append("---");
            }
        }
        System.out.println(noIngredientsList);

        StringBuilder allergiesList = new StringBuilder("Внимание! У вас аллергия на: ---");

        for (int i = 0; i < this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients().length; i++) {
            if (allergies.contains(this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i])) {
                allergiesList.append(this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i]).append("---");
            }
        }
        System.out.println(allergiesList);
    }

    private void printLastRecipe() {
        this.recipesBase.updateLastRecipe();
        Recipe lastRecipe = this.recipesBase.getLastRecipe();
        System.out.println(lastRecipe.getName() + "(" + lastRecipe.getMealType() + ")");
        for (int i = 0; i < lastRecipe.getIngredients().length; i++) {
            System.out.print((i + 1) + " - ");
            System.out.println(lastRecipe.getIngredients()[i]);
        }

        System.out.println("Можно заменить:");

        for (int i = 0; i < lastRecipe.getIngredients().length; i++) {
            for (String[] change : this.changes) {
                if (lastRecipe.getIngredients()[i].contains(change[0]) ||
                        lastRecipe.getIngredients()[i].contains(change[1])
                ) {
                    System.out.println(change[0] + "<->" + change[1]);
                }
            }
        }

        System.out.println();

        System.out.println("Рецепт: " + lastRecipe.getRecipeText());

        StringBuilder appliancesList = new StringBuilder("Рекомендуемые приборы: ---");

        for (int i = 0; i < lastRecipe.getAppliance().length; i++) {
            appliancesList.append(lastRecipe.getAppliance()[i]).append("---");
        }
        System.out.println(appliancesList);

        StringBuilder noAppliancesList = new StringBuilder("Недостающие приборы: ---");

        for (int i = 0; i < lastRecipe.getAppliance().length; i++) {
            if (!this.appliances.contains(lastRecipe.getAppliance()[i])) {
                noAppliancesList.append(lastRecipe.getAppliance()[i]).append("---");
            }
        }
        System.out.println(noAppliancesList);

        StringBuilder noIngredientsList = new StringBuilder("Недостающие ингредиенты: ---");

        for (int i = 0; i < lastRecipe.getIngredients().length; i++) {
            if (!this.foodstuff.contains(lastRecipe.getIngredients()[i])) {
                noIngredientsList.append(lastRecipe.getIngredients()[i]).append("---");
            }
        }
        System.out.println(noIngredientsList);

        StringBuilder allergiesList = new StringBuilder("Внимание! У вас аллергия на: ---");

        for (int i = 0; i < lastRecipe.getIngredients().length; i++) {
            if (this.allergies.contains(lastRecipe.getIngredients()[i])) {
                allergiesList.append(lastRecipe.getIngredients()[i]).append("---");
            }
        }
        System.out.println(allergiesList);
    }

    private void printChosenRecipe() {
        for (int i = 0; i < this.recipes.size(); i++) {
            System.out.print((i + 1) + " - ");
            System.out.println(this.recipes.get(i).getName() + "(" + this.recipes.get(i).getMealType() + ")");
        }

        System.out.println("Выберите рецепт:");
        String choiceId = this.in.nextLine();

        this.recipesBase.overwriteLastRecipe(this.recipes.get(Integer.parseInt(choiceId) - 1));

        System.out.println(this.recipes.get(Integer.parseInt(choiceId) - 1).getName());
        System.out.println("Ингредиенты:");
        for (int i = 0; i < this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients().length; i++) {
            System.out.print((i + 1) + " - ");
            System.out.println(this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients()[i]);
        }

        System.out.println("Можно заменить:");

        for (int i = 0; i < this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients().length; i++) {
            for (String[] change : this.changes) {
                if (this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients()[i].contains(change[0]) ||
                        this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients()[i].contains(change[1])
                ) {
                    System.out.println(change[0] + "<->" + change[1]);
                }
            }
        }

        System.out.println();

        System.out.println("Рецепт: " + this.recipes.get(Integer.parseInt(choiceId) - 1).getRecipeText());

        StringBuilder appliancesList = new StringBuilder("Рекомендуемые приборы: ---");

        for (int i = 0; i < this.recipes.get(Integer.parseInt(choiceId) - 1).getAppliance().length; i++) {
            appliancesList.append(this.recipes.get(Integer.parseInt(choiceId) - 1).getAppliance()[i]).append("---");
        }
        System.out.println(appliancesList);

        StringBuilder noAppliancesList = new StringBuilder("Недостающие приборы: ---");

        for (int i = 0; i < this.recipes.get(Integer.parseInt(choiceId) - 1).getAppliance().length; i++) {
            if (!this.appliances.contains(this.recipes.get(Integer.parseInt(choiceId) - 1).getAppliance()[i])) {
                noAppliancesList.append(this.recipes.get(Integer.parseInt(choiceId) - 1).getAppliance()[i]).append("---");
            }
        }
        System.out.println(noAppliancesList);

        StringBuilder noIngredientsList = new StringBuilder("Недостающие ингредиенты: ---");

        for (int i = 0; i < this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients().length; i++) {
            if (!this.foodstuff.contains(this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients()[i])) {
                noIngredientsList.append(this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients()[i]).append("---");
            }
        }
        System.out.println(noIngredientsList);

        StringBuilder allergiesList = new StringBuilder("Внимание! У вас аллергия на: ---");

        for (int i = 0; i < this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients().length; i++) {
            if (this.allergies.contains(this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients()[i])) {
                allergiesList.append(this.recipes.get(Integer.parseInt(choiceId) - 1).getIngredients()[i]).append("---");
            }
        }
        System.out.println(allergiesList);
    }

    private void printAddNewFoodstuff() {
        System.out.println("Введите новый ингредиент: ");
        String newIngredient = this.in.nextLine();

        this.foodstuffBase.addFoodstuffToBase(newIngredient);
        this.foodstuffBase.updateFoodstuffBase();
        this.foodstuff = this.foodstuffBase.getAllFoodstuff();
    }

    private void printAddNewAppliance() {
        System.out.println("Введите новый прибор: ");
        String newAppliance = this.in.nextLine();

        this.appliancesBase.addAppliancesToBase(newAppliance);
        this.appliancesBase.updateAppliancesBase();
        this.appliances = this.appliancesBase.getAppliances();
    }

    private void printAllergies() {
        System.out.print("У вас аллергия на: ---");
        for (String allergy : this.allergies) {
            System.out.print(allergy + "---");
        }
        System.out.println();
    }

    private void printAppliances() {
        System.out.println("Доступные приборы:");
        for (String appliance : this.appliances) {
            System.out.println(appliance);
        }
        System.out.println();
    }

    public Logic(RecipesBase recipesBase, FoodstuffBase foodstuffBase, AppliancesBase appliancesBase, ArrayList<Recipe> recipes, ArrayList<String> allFoodstuff, ArrayList<String> allergies, ArrayList<String> appliances, ArrayList<String[]> changes) {
        this.recipesBase = recipesBase;
        this.foodstuffBase = foodstuffBase;
        this.appliancesBase = appliancesBase;
        this.recipes = recipes;
        this.foodstuff = allFoodstuff;
        this.allergies = allergies;
        this.appliances = appliances;
        this.changes = changes;
    }
}
