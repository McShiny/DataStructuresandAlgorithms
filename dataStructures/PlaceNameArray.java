package dataStructures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Stores and manages a collection of PlaceNameEntry objects in an array.
 * This class supports loading records from a file, inserting individual records,
 * searching for places, sorting by place name, and retrieving array metadata.
 */
public class PlaceNameArray {
    
    private PlaceNameEntry[] placeArray;
    private String[] fileInputOrder;
    private int searchComparisons;
    private int loadedPlaces = 0;
    
    /**
     * Constructs a PlaceNameArray with a fixed maximum capacity.
     *
     * @param maxRecords the maximum number of records the array can hold
     */
    public PlaceNameArray(int maxRecords) {
        placeArray = new PlaceNameEntry[maxRecords];
    }
    
    /**
     * Constructs a PlaceNameArray with a fixed maximum capacity and loads records
     * from the specified file.
     *
     * @param maxRecords the maximum number of records to load
     * @param filePath the path to the input file
     */
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
                if (!isInArray(place)) {
                    placeArray[loadedPlaces + loaded] = place;
                    loaded++;
                }
            }
            loadedPlaces += loaded;

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Constructs a deep copy of an existing PlaceNameArray.
     *
     * @param places the PlaceNameArray to copy
     */
    public PlaceNameArray (PlaceNameArray places) {
        this.loadedPlaces = places.getLoadedPlaces();
        this.placeArray = new PlaceNameEntry[loadedPlaces];

        for (int i = 0; i < loadedPlaces; i++) {
            this.placeArray[i] = new PlaceNameEntry(places.getPlaceArray()[i]);
        }

    }
    
    /**
     * Constructs a deep copy of an existing PlaceNameArray with additional capacity.
     *
     * @param places the PlaceNameArray to copy
     * @param extra the number of additional array positions to allocate
     */
    public PlaceNameArray (PlaceNameArray places, int extra) {
        this.loadedPlaces = places.getLoadedPlaces();
        this.placeArray = new PlaceNameEntry[places.getPlaceArray().length + extra];

        for (int i = 0; i < loadedPlaces; i++) {
            this.placeArray[i] = new PlaceNameEntry(places.getPlaceArray()[i]);
        }

    }

    /**
     * Loads records from a file into the array up to the specified maximum number
     * of records.
     *
     * @param maxRecords the maximum number of records to load
     * @param filePath the path to the input file
     */
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
                if (!isInArray(place)) {
                    placeArray[loadedPlaces + loaded] = place;
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
            if ((placeArray[i] != null) && (placeArray[i].placeName.compareTo(place.placeName) == 0)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Loads a single place record into the array if it is not already present.
     *
     * @param place the PlaceNameEntry object to add
     */
    public void loadRecord(PlaceNameEntry place) {
        if (!isInArray(place)) {
            placeArray[loadedPlaces] = place;
            loadedPlaces++;
        }
    }
    
    /**
     * Searches for a place by name and returns its string representation.
     *
     * @param place the name of the place to search for
     * @return the string representation of the matching place, or
     *         "Place not found in database" if no match is found
     */
    public String findPlace(String place) {
        searchComparisons = 0;
        for (int i = 0; i < loadedPlaces; i++) {
            searchComparisons++;
            if (placeArray[i].placeName.compareTo(place) == 0)
                return placeArray[i].toString();
        }   

        return "Place not found in database";

    }
    
    /**
     * Searches for a place by name and returns the matching PlaceNameEntry object.
     *
     * @param place the name of the place to search for
     * @param r an unused parameter used to distinguish this overloaded method
     * @return the matching PlaceNameEntry if found, otherwise null
     */
    public PlaceNameEntry findPlace(String place, String r) {
        searchComparisons = 0;
        for (int i = 0; i < loadedPlaces; i++) {
            searchComparisons++;
            if (placeArray[i].placeName.compareTo(place) == 0)
                return placeArray[i];
        }   

        return null;

    }
    
    /**
     * Returns the number of places currently loaded in the array.
     *
     * @return the number of loaded place records
     */
    public int getLoadedPlaces() {
        return loadedPlaces;
    }

    /**
     * Returns the underlying array of place records.
     *
     * @return the array containing PlaceNameEntry objects
     */
    public PlaceNameEntry[] getPlaceArray() {
        return placeArray;
    }
    
    /**
     * Returns the number of comparisons made during the most recent search.
     *
     * @return the number of search comparisons
     */
    public int getSearchComparisons() {
        return searchComparisons;
    }

    /**
     * Returns a sorted copy of the given PlaceNameArray based on place name.
     *
     * @param places the PlaceNameArray to sort
     * @return a new PlaceNameArray containing the sorted records
     */
    public PlaceNameArray sortByName(PlaceNameArray places) {
        PlaceNameArray sortedPlaces = new PlaceNameArray(places);
        Arrays.sort(sortedPlaces.getPlaceArray(), Comparator.comparing(p -> p.placeName));

        return sortedPlaces;
    }

    /**
     * Returns a string representation of all loaded place records.
     *
     * @return a multi-line string containing all loaded places
     */
    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < loadedPlaces; i++) {
            output += placeArray[i].toString() + "\n";
        }

        return output;
    }    
}
