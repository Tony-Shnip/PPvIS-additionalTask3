package com.company.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class Logic {

    private final RecipesBase recipesBase;
    private final FoodstuffBase foodstuffBase;
    private final ArrayList<Recipe> recipes;
    private ArrayList<String> foodstuff;
    private final ArrayList<String> allergies;
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
            System.out.println("7 - аллергии,");
            System.out.println("8 - закончить работу.");
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
                    printAllergies();
                    break;
                case "8":
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
        System.out.println("Рецепт: " + this.recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getRecipeText());

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
        System.out.println("Рецепт: " + lastRecipe.getRecipeText());

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
        System.out.println("Рецепт: " + this.recipes.get(Integer.parseInt(choiceId) - 1).getRecipeText());

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

    private void printAllergies() {
        System.out.print("У вас аллергия на: ---");
        for (String allergy : this.allergies) {
            System.out.print(allergy + "---");
        }
        System.out.println();
    }

    public Logic(RecipesBase recipesBase, FoodstuffBase foodstuffBase ,ArrayList<Recipe> recipes, ArrayList<String> allFoodstuff, ArrayList<String> allergies) {
        this.recipesBase = recipesBase;
        this.foodstuffBase = foodstuffBase;
        this.recipes = recipes;
        this.foodstuff = allFoodstuff;
        this.allergies = allergies;
    }
}
