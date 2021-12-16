package com.company.basic;

import com.company.basic.assets.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RecipesBase implements Constants {

    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    private Recipe lastRecipe;

    private void updateRecipesBase() {
        updateLastRecipe();
        try {
            String path = new File("").getAbsolutePath();
            File file = new File(path + FILE_RECIPES);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] newLine;
            int newLineLength;
            int textKey = 0;

            while (line != null) {
                if (textKey == 0) {
                    newLine = line.split(",");
                    newLineLength = newLine.length;
                    this.recipes.add(new Recipe(newLine[0], Arrays.copyOfRange(newLine, 1, newLineLength - 1), newLine[newLineLength - 1]));
                    textKey = 1;
                } else {
                    if (textKey == 1){
                        recipes.get(recipes.size() - 1).setRecipeText(line);
                        textKey = 2;
                    } else {
                        if (textKey == 2) {
                            newLine = line.split(",");
                            recipes.get(recipes.size() - 1).setAppliance(newLine);
                            textKey = 0;
                        }
                    }
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateLastRecipe() {
        try {
            String path = new File("").getAbsolutePath();
            File file = new File(path + FILE_LAST_RECIPE);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] newLine;
            int newLineLength;
            int textKey = 0;

            while (line != null) {
                if (textKey == 0) {
                    newLine = line.split(",");
                    newLineLength = newLine.length;
                    this.lastRecipe = new Recipe(newLine[0], Arrays.copyOfRange(newLine, 1, newLineLength - 1), newLine[newLineLength - 1]);
                    textKey = 1;
                } else {
                    if (textKey == 1) {
                        lastRecipe.setRecipeText(line);
                        textKey = 2;
                    } else {
                        if (textKey == 2) {
                            newLine = line.split(",");
                            lastRecipe.setAppliance(newLine);
                            textKey = 0;
                        }
                    }
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void overwriteLastRecipe(Recipe recipe) {
        String path = new File("").getAbsolutePath();
        File file = new File(path + FILE_LAST_RECIPE);
        try(FileWriter fw = new FileWriter(file))
        {
            PrintWriter pw = new PrintWriter(path + FILE_LAST_RECIPE);
            pw.close();

            String firstLine;
            String thirdLine = "";

            firstLine = recipe.getName() + ",";
            for (int i = 0; i < recipe.getIngredients().length; i++) {
                firstLine += recipe.getIngredients()[i] + ",";
            }
            firstLine += recipe.getMealType();

            for (int i = 0; i < recipe.getAppliance().length; i++) {
                thirdLine += recipe.getAppliance()[i] + ",";
            }

            fw.write(firstLine + "\n");
            fw.write(recipe.getRecipeText() + "\n");
            fw.write(thirdLine);

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public RecipesBase() {
        updateRecipesBase();
    }

    public Recipe getLastRecipe() {
        return this.lastRecipe;
    }

    public ArrayList<Recipe> getRecipes() {
        return this.recipes;
    }
}