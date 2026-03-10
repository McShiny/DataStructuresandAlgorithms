import dataStructures.PlaceNameArray;
import dataStructures.PlaceNameBST;
import dataStructures.PlaceNameEntry;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PlaceExperiment {
    public static void main(String[] args) {
        
        int[] test = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        String filePathData = "files/SAplaceNames.csv";
        String filePathOptimal = "files/SAPlaceNamesOptimal.txt";
        String filePathSearches = "files/SearchQueries.txt";
        String[] toSearch = new String[50];
        String[] optimalOrder = new String[12499];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathSearches))) {
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                String[] temp = line.split("\s");
                for (int i = 0; i < 5; i++) {
                    toSearch[index] = line.split("\s")[i];
                    index++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathOptimal))) {
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                optimalOrder[index] = line;
                index++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

        double[] arrValues = new double[test.length];
        for (int i = 0; i < test.length; i++) {
            arrValues[i] = testArray(test[i], toSearch, filePathData);
        }

        double[] asIsTree = new double[test.length];
        double[] sortedTree = new double[test.length];
        double[] optimalTree = new double[test.length];

        for (int i = 0; i < test.length; i++) {
            asIsTree[i] = testBST(test[i], toSearch, filePathData);
            sortedTree[i] = testSortedBST(test[i], toSearch, filePathData);
            optimalTree[i] = testOptimalBST(test[i], toSearch, filePathData, optimalOrder);
        }

        System.out.println("N       Array   BST(as-is)  BST(sorted)  BST(optimal)");
        for (int i = 0; i < test.length; i++) {
            System.out.printf("%s   %s     %s       %s        %s\n", test[i], arrValues[i], asIsTree[i], sortedTree[i], optimalTree[i]);
        }

    }

    static double testArray(int N, String[] search, String data) {
        int total = 0;
        PlaceNameArray arr = new PlaceNameArray(N);
        arr.loadRecords(N, data);

        for (int i = 0; i < 50; i++) {
            arr.findPlace(search[i]);
            total += arr.getSearchComparisons();
        }   
        
        return total / 50.0;
    }

    static double testBST(int N, String[] search, String data) {
        int total = 0;
        
        PlaceNameArray places = new PlaceNameArray(N + 2000);
        places.loadRecords(N + 2000, data);
        
        PlaceNameBST tree = new PlaceNameBST(N ,places);

        for (int i = 0; i < 50; i++) {
            tree.findPlace(search[i]);
            total += tree.getSearchComparisons();
        }
        return total / 50.0;
    }

    static double testSortedBST(int N, String[] search, String data) {
        int total = 0;
        
        PlaceNameArray places = new PlaceNameArray(N + 2000);
        places.loadRecords((int) N + 2000, data);
        PlaceNameArray sortedPlaces = places.sortByName(places);
        
        PlaceNameBST tree = new PlaceNameBST(N, sortedPlaces);

        for (int i = 0; i < 50; i++) {
            tree.findPlace(search[i]);
            total += tree.getSearchComparisons();
        }
        return total / 50.0;
    }

    static double testOptimalBST(int N, String[] search, String data, String[] order) {
        int total = 0;
        
        PlaceNameArray places = new PlaceNameArray(N + 2000);
        places.loadRecords((int) N + 2000, data);

        PlaceNameArray optimalPlaces = new PlaceNameArray(N + 2000);
        int index = 0;

        while (optimalPlaces.getLoadedPlaces() < N + 2000 && index < 12499) {
            PlaceNameEntry place = places.findPlace(order[index], "PlaceNameEntry");
            if (place != null) {
                optimalPlaces.loadRecord(place);
            }
            index++;
        }
        
        PlaceNameBST tree = new PlaceNameBST(N, optimalPlaces);

        for (int i = 0; i < 50; i++) {
            tree.findPlace(search[i]);
            total += tree.getSearchComparisons();
        }
        return total / 50.0;
    }

}
