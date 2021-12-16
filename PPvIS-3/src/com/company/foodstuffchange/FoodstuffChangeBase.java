package com.company.foodstuffchange;

import com.company.foodstuffchange.assets.Constants;

import java.io.*;
import java.util.ArrayList;

public class FoodstuffChangeBase implements Constants {
    private ArrayList<String[]> changes = new ArrayList<String[]>();

    public void updateFoodstuffChangeBase() {
        try {
            String path = new File("").getAbsolutePath();
            File file = new File(path + FILE_FOODSTUFF_CHANGE);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] newLine;

            while (line != null) {
                newLine = line.split("-");
                this.changes.add(newLine);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FoodstuffChangeBase() {
        updateFoodstuffChangeBase();
    }

    public ArrayList<String[]> getAllChanges() {
        return this.changes;
    }
}
