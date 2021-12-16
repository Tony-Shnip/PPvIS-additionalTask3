package com.company.allergy;

import com.company.allergy.assets.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AllergiesBase implements Constants {
    private final ArrayList<String> allergies = new ArrayList<>();

    public void updateAllergiesBase() {
        try {
            String path = new File("").getAbsolutePath();
            File file = new File(path + FILE_ALLERGY);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] newLine;

            while (line != null) {
                newLine = line.split(",");
                for(String elem : newLine) {
                    this.allergies.add(elem);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AllergiesBase(){
        updateAllergiesBase();
    }

    public ArrayList<String> getAllergies() {
        return this.allergies;
    }
}
