package com.company.basic;

import com.company.basic.assets.*;

import java.io.*;
import java.util.ArrayList;

public class FoodstuffBase implements Constants {
    private ArrayList<String> foodstuff = new ArrayList<String>();
    private int amountOfFoodstuff;

    public void updateFoodstuffBase() {
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

    public void addFoodstuffToBase(String ingredient) {
        String path = new File("").getAbsolutePath();
        try
        {
            FileWriter fw = new FileWriter(path + FILE_FOODSTUFF, true);
            BufferedWriter bufferWriter = new BufferedWriter(fw);
            bufferWriter.write(ingredient + ",");
            bufferWriter.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public FoodstuffBase() {
        updateFoodstuffBase();
    }

    public ArrayList<String> getAllFoodstuff() {
        return this.foodstuff;
    }
}
