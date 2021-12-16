package com.company.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class Logic {

    public void Start(RecipesBase recipesBase, FoodstuffBase foodstuffBase ,ArrayList<Recipe> recipes, ArrayList<String> allFfoodstuff) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> foodstuff = allFfoodstuff;

        while (true) {
            System.out.println("\nВыберите операцию:");
            System.out.println("1 - доступные рецепты,");
            System.out.println("2 - добавленные ингредиенты,");
            System.out.println("3 - выбрать рецепт по ингредиенту,");
            System.out.println("4 - продолжить готовку,");
            System.out.println("5 - выбрать рецепт из списка,");
            System.out.println("6 - добавить ингредиент,");
            System.out.println("7 - закончить работу.");
            String input = in.nextLine();

            switch (input) {
                case "1":
                    for (int i = 0; i < recipes.size(); i++) {
                        System.out.print((i + 1) + " - ");
                        System.out.println(recipes.get(i).getName() + "(" + recipes.get(i).getMealType() + ")");
                    }
                    break;
                case "2":
                    for (int i = 0; i < foodstuff.size(); i++) {
                        System.out.println(foodstuff.get(i));
                    }
                    break;
                case "3":
                    System.out.println("Выберите тип блюда:");
                    System.out.println("1 - Завтрак,");
                    System.out.println("2 - Обед,");
                    System.out.println("3 - Ужин.");
                    String mealTypeId = in.nextLine();

                    System.out.println("Введите желаемый игредиент:");
                    String foodstuffName = in.nextLine();

                    int[] mealTypeIds = new int[50];
                    int mealCount = 0;
                    for (int i = 0; i < recipes.size(); i++) {
                        if ((mealTypeId.equals("1") && recipes.get(i).getMealType().equals("Завтрак")) ||
                            (mealTypeId.equals("2") && recipes.get(i).getMealType().equals("Обед")) ||
                            (mealTypeId.equals("3") && recipes.get(i).getMealType().equals("Ужин"))) {

                            for (int j = 0; j < recipes.get(i).getIngredients().length; j++) {
                                if (recipes.get(i).getIngredients()[j].equals(foodstuffName)) {
                                    mealCount++;
                                    System.out.print((mealCount) + " - ");
                                    System.out.println(recipes.get(i).getName());
                                    mealTypeIds[mealCount - 1] = i;
                                }
                            }
                        }
                    }
                    if (mealCount == 0) {
                        System.out.println("Нет подходящего рецепта.");
                        break;
                    }

                    System.out.println("Выберите рецепт:");
                    String recipeId = in.nextLine();

                    recipesBase.overwriteLastRecipe(recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]));

                    System.out.println(recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getName());
                    System.out.println("Ингредиенты:");
                    for (int i = 0; i < recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients().length; i++) {
                        System.out.print((i + 1) + " - ");
                        System.out.println(recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i]);
                    }
                    System.out.println("Рецепт: " + recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getRecipeText());

                    String absenceIngredients = "Недостающие ингредиенты: ---";

                    for (int i = 0; i < recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients().length; i++) {
                        if (!foodstuff.contains(recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i])) {
                            absenceIngredients += recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i] + "---";
                        }
                    }
                    System.out.println(absenceIngredients);
                    break;
                case "4":
                    recipesBase.updateLastRecipe();
                    Recipe lastRecipe = recipesBase.getLastRecipe();
                    System.out.println(lastRecipe.getName() + "(" + lastRecipe.getMealType() + ")");
                    for (int i = 0; i < lastRecipe.getIngredients().length; i++) {
                        System.out.print((i + 1) + " - ");
                        System.out.println(lastRecipe.getIngredients()[i]);
                    }
                    System.out.println("Рецепт: " + lastRecipe.getRecipeText());

                    String noIngredients = "Недостающие ингредиенты: ---";

                    for (int i = 0; i < lastRecipe.getIngredients().length; i++) {
                        if (!foodstuff.contains(lastRecipe.getIngredients()[i])) {
                            noIngredients += lastRecipe.getIngredients()[i] + "---";
                        }
                    }
                    System.out.println(noIngredients);
                    break;
                case "5":
                    for (int i = 0; i < recipes.size(); i++) {
                        System.out.print((i + 1) + " - ");
                        System.out.println(recipes.get(i).getName() + "(" + recipes.get(i).getMealType() + ")");
                    }

                    System.out.println("Выберите рецепт:");
                    String choiceId = in.nextLine();

                    recipesBase.overwriteLastRecipe(recipes.get(Integer.parseInt(choiceId) - 1));

                    System.out.println(recipes.get(Integer.parseInt(choiceId) - 1).getName());
                    System.out.println("Ингредиенты:");
                    for (int i = 0; i < recipes.get(Integer.parseInt(choiceId) - 1).getIngredients().length; i++) {
                        System.out.print((i + 1) + " - ");
                        System.out.println(recipes.get(Integer.parseInt(choiceId) - 1).getIngredients()[i]);
                    }
                    System.out.println("Рецепт: " + recipes.get(Integer.parseInt(choiceId) - 1).getRecipeText());

                    String noIngredientsList = "Недостающие ингредиенты: ---";

                    for (int i = 0; i < recipes.get(Integer.parseInt(choiceId) - 1).getIngredients().length; i++) {
                        if (!foodstuff.contains(recipes.get(Integer.parseInt(choiceId) - 1).getIngredients()[i])) {
                            noIngredientsList += recipes.get(Integer.parseInt(choiceId) - 1).getIngredients()[i] + "---";
                        }
                    }
                    System.out.println(noIngredientsList);

                    break;
                case "6":
                    System.out.println("Введите новый ингредиент: ");
                    String newIngredient = in.nextLine();

                    foodstuffBase.addFoodstuffToBase(newIngredient);
                    foodstuffBase.updateFoodstuffBase();
                    foodstuff = foodstuffBase.getAllFoodstuff();

                    break;
                case "7":
                    return;
                default:
                    break;
            }
        }

    }
}
