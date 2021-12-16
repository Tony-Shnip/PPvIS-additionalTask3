package com.company.appliance;

import com.company.appliance.assets.Constants;

import java.io.*;
import java.util.ArrayList;

public class AppliancesBase implements Constants {
    private final ArrayList<String> appliances = new ArrayList<String>();

    public void updateAppliancesBase() {
        try {
            String path = new File("").getAbsolutePath();
            File file = new File(path + FILE_APPLIANCE);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] newLine;

            while (line != null) {
                newLine = line.split(",");
                for(int i = 0; i < newLine.length; i++) {
                    this.appliances.add(newLine[i]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAppliancesToBase(String appliance) {
        String path = new File("").getAbsolutePath();
        try
        {
            FileWriter fw = new FileWriter(path + FILE_APPLIANCE, true);
            BufferedWriter bufferWriter = new BufferedWriter(fw);
            bufferWriter.write(appliance + ",");
            bufferWriter.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public AppliancesBase(){
        updateAppliancesBase();
    }

    public ArrayList<String> getAppliances() {
        return this.appliances;
    }
}
