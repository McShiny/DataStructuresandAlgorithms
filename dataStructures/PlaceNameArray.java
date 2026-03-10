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

    public PlaceNameArray(int maxRecords, String filePath) {
        placeArray = new PlaceNameEntry[maxRecords];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (loadedPlaces == 0) {
                fileInputOrder = line.split(",");
            }
            int loaded = 0;
            while ((line = reader.readLine()) != null && loaded < maxRecords) {
                PlaceNameEntry place = new PlaceNameEntry(line.split(","));    
                if (isInArray(place)) {
                    System.out.println(!isInArray(place));
                }
                if (!isInArray(place)) {
                    placeArray[loadedPlaces + loaded] = place;
                    if (loadedPlaces + loaded > 0)
                        placeArray[loadedPlaces + loaded - 1] = place;
                    loaded++;
                }
            }
            loadedPlaces += loaded;

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public PlaceNameArray (PlaceNameArray places) {
        this.loadedPlaces = places.getLoadedPlaces();
        this.placeArray = new PlaceNameEntry[loadedPlaces];

        for (int i = 0; i < loadedPlaces; i++) {
            this.placeArray[i] = new PlaceNameEntry(places.getPlaceArray()[i]);
        }

    }

    public PlaceNameArray (PlaceNameArray places, int extra) {
        this.loadedPlaces = places.getLoadedPlaces();
        this.placeArray = new PlaceNameEntry[places.getPlaceArray().length + extra];

        for (int i = 0; i < loadedPlaces; i++) {
            this.placeArray[i] = new PlaceNameEntry(places.getPlaceArray()[i]);
        }

    }

    public void loadRecords(int maxRecords, String filePath) {

        if (maxRecords + loadedPlaces > placeArray.length) {
            PlaceNameArray temp = new PlaceNameArray(this, (maxRecords + loadedPlaces) - placeArray.length);
            placeArray = temp.getPlaceArray();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (loadedPlaces == 0) {
                fileInputOrder = line.split(",");
            }
            int loaded = 0;
            while ((line = reader.readLine()) != null && loaded < maxRecords) {
                PlaceNameEntry place = new PlaceNameEntry(line.split(","));    
                if (isInArray(place)) {
                    System.out.println(!isInArray(place));
                }
                if (!isInArray(place)) {
                    placeArray[loadedPlaces + loaded] = place;
                    if (loadedPlaces + loaded > 0)
                        placeArray[loadedPlaces + loaded - 1] = place;
                    loaded++;
                }
            }
            loadedPlaces += loaded;

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    } 

    private boolean isInArray(PlaceNameEntry place) {
        for (int i = 0; i < placeArray.length; i++) {
            if (placeArray[i] == place) {
                return true;
            }
        }

        return false;
    }

    public void loadRecord(PlaceNameEntry place) {
        if (!isInArray(place)) {
            placeArray[loadedPlaces] = place;
            loadedPlaces++;
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

    public PlaceNameEntry findPlace(String place, String r) {
        searchComparisons = 0;
        for (int i = 0; i < loadedPlaces - 1; i++) {
            searchComparisons++;
            if (placeArray[i].placeName.compareTo(place) == 0)
                return placeArray[i];
        }   

        return null;

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

    public PlaceNameArray sortByName(PlaceNameArray places) {
        PlaceNameArray sortedPlaces = new PlaceNameArray(places);
        Arrays.sort(sortedPlaces.getPlaceArray(), Comparator.comparing(p -> p.placeName));

        return sortedPlaces;
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
