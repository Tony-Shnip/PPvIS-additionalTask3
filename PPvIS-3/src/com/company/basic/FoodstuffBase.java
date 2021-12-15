package com.company.basic;

import com.company.basic.assets.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FoodstuffBase implements Constants {
    private ArrayList<String> foodstuff = new ArrayList<String>();
    private int amountOfFoodstuff;

    private void updateFoodstuffBase() {
        try {
            String path = new File("").getAbsolutePath();
            File file = new File(path + FILE_FOODSTUFF);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] newLine;
            this.amountOfFoodstuff = 0;

            while (line != null) {
                newLine = line.split(",");
                for(int i = 0; i < newLine.length; i++) {
                    this.foodstuff.add(newLine[i]);
                    this.amountOfFoodstuff++;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FoodstuffBase() {
        updateFoodstuffBase();
    }

    public ArrayList<String> getAllFoodstuff() {
        return this.foodstuff;
    }
}
