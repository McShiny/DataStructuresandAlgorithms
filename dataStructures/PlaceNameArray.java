package dataStructures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlaceNameArray {
    
    private PlaceNameEntry[] placeArray;
    private String[] fileInputOrder;
    private int searchComparisons;
    private int loadedPlaces = 0;

    public PlaceNameArray(int maxRecords) {
        placeArray = new PlaceNameEntry[maxRecords];
    }

    public void loadRecords(int maxRecords, String filePath) {
        // load records to a maximum of N
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = loadedPlaces;

            while ((line = reader.readLine()) != null && index < maxRecords) {
                if (index == 0) {
                    fileInputOrder = line.split(",");
                    index++;
                } else {
                    placeArray[index - 1] = new PlaceNameEntry(line.split(","));
                    index++;
                }
            }

            loadedPlaces = index;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void findPlace() {
        // 
    }

    public String toString() {
        String output = "";

        for (int i = 0; i < loadedPlaces - 1; i++) {
            output += placeArray[i].toString() + "\n";
        }

        return output;
    }
}
