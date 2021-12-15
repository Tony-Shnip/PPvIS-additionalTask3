package com.company.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class Logic {
    public void Start(ArrayList<Recipe> recipes, ArrayList<String> foodstuff) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите операцию:");
            System.out.println("1 - доступные рецепты,");
            System.out.println("2 - добавленные ингредиенты,");
            System.out.println("3 - выбрать рецепт,");
            System.out.println("4 - продолжить готовку,");
            System.out.println("5 - закончить работу.");
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
                    int[] mealTypeIds = new int[50];
                    int mealCount = 0;
                    for (int i = 0; i < recipes.size(); i++) {
                        if ((mealTypeId.equals("1") && recipes.get(i).getMealType().equals("Завтрак")) ||
                            (mealTypeId.equals("2") && recipes.get(i).getMealType().equals("Обед")) ||
                            (mealTypeId.equals("3") && recipes.get(i).getMealType().equals("Ужин"))) {

                            mealCount++;
                            System.out.print((mealCount) + " - ");
                            System.out.println(recipes.get(i).getName());
                            mealTypeIds[mealCount - 1] = i;
                        }
                    }
                    System.out.println("Выберите рецепт:");
                    String recipeId = in.nextLine();
                    System.out.println(recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getName());
                    System.out.println("Ингредиенты:");
                    for (int i = 0; i < recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients().length; i++) {
                        System.out.print((i + 1) + " - ");
                        System.out.println(recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getIngredients()[i]);
                    }
                    System.out.println("Рецепт: " + recipes.get(mealTypeIds[Integer.parseInt(recipeId) - 1]).getRecipeText());
                    break;
                case "4":
                    break;
                case "5":
                    return;
                default:
                    break;
            }
        }

    }
}
