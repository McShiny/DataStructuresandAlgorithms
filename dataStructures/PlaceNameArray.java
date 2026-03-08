package dataStructures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlaceNameArray {
    
    private PlaceNameEntry[] placeArray;
    private String[] fileInputOrder;
    private int searchComparisons = 0;
    private int loadedPlaces = 0;

    public PlaceNameArray(int maxRecords) {
        placeArray = new PlaceNameEntry[maxRecords];
    }

    public void loadRecords(int maxRecords, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            fileInputOrder = fileInputOrder = line.split(",");
            int loaded = 0;

            while ((line = reader.readLine()) != null && loaded < maxRecords + 1) {
                    placeArray[loadedPlaces + loaded] = new PlaceNameEntry(line.split(","));
                    loaded++;
                }

            loadedPlaces += loaded;

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String findPlace(String place) {
        for (int i = 0; i < loadedPlaces - 1; i++) {
            if (placeArray[i].placeName.compareTo(place) == 0)
                return placeArray[i].toString();
        }   

        return "Place not found in database";

    }

    public String toString() {
        String output = "";

        for (int i = 0; i < loadedPlaces - 1; i++) {
            output += placeArray[i].toString() + "\n";
        }

        return output;
    }
}
