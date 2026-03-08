package dataStructures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class PlaceNameArray {
    
    private PlaceNameEntry[] placeArray;
    private String[] fileInputOrder;
    private int searchComparisons;
    private int loadedPlaces = 0;

    public PlaceNameArray(int maxRecords) {
        placeArray = new PlaceNameEntry[maxRecords];
    }

    public PlaceNameArray (PlaceNameArray places) {
        this.loadedPlaces = places.getLoadedPlaces();
        this.placeArray = new PlaceNameEntry[loadedPlaces];

        for (int i = 0; i < loadedPlaces; i++) {
            this.placeArray[i] = new PlaceNameEntry(places.getPlaceArray()[i]);
        }

    }

    public void loadRecords(int maxRecords, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (loadedPlaces == 0) {
                fileInputOrder = line.split(",");
            }
            int loaded = 0;
            while ((line = reader.readLine()) != null && loaded < maxRecords) {
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
        searchComparisons = 0;
        for (int i = 0; i < loadedPlaces - 1; i++) {
            searchComparisons++;
            if (placeArray[i].placeName.compareTo(place) == 0)
                return placeArray[i].toString();
        }   

        return "Place not found in database";

    }

    public int getLoadedPlaces() {
        return loadedPlaces;
    }

    public PlaceNameEntry[] getPlaceArray() {
        return placeArray;
    }

    public int getSearchComparisons() {
        return searchComparisons;
    }

    public PlaceNameEntry[] sortByName(PlaceNameArray places) {
        PlaceNameArray sortedPlaces = new PlaceNameArray(places);
        Arrays.sort(sortedPlaces.getPlaceArray(), Comparator.comparing(p -> p.placeName));

        return sortedPlaces.getPlaceArray();
    }
    
    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < loadedPlaces; i++) {
            output += placeArray[i].toString() + "\n";
        }

        return output;
    }

    
}
