package com.company.basic;

import com.company.basic.assets.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RecipesBase implements Constants {

    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

    private void updateRecipesBase() {
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
                    recipes.get(recipes.size() - 1).setRecipeText(line);
                    textKey = 0;
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RecipesBase() {
        updateRecipesBase();
    }

    public ArrayList<Recipe> getRecipes() {
        return this.recipes;
    }
}