import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Runs an experiment that compares average search comparisons for place-name
 * lookups using an array, a binary search tree built in file order, a binary
 * search tree built from sorted data, and a binary search tree built from an
 * optimal insertion order.
 */
public class PlaceExperiment {
    public static void main(String[] args) {
        
        int[] test = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        String filePathData = "SAplaceNames.csv";
        String filePathOptimal = "SAPlaceNamesOptimal.txt";
        String filePathSearches = "SearchQueries.txt";
        String[] toSearch = new String[50];
        String[] optimalOrder = new String[12499];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathSearches))) {
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(",");
                for (int i = 0; i < 5; i++) {
                    toSearch[index] = line.split(",")[i];
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

        PlaceNameArray places = new PlaceNameArray(14000);
        places.loadRecords(14000, filePathData);
        PlaceNameArray sortedPlaces = places.sortByName(places);

        for (int i = 0; i < test.length; i++) {
            asIsTree[i] = testBST(test[i], toSearch, filePathData);
            sortedTree[i] = testSortedBST(test[i], toSearch, sortedPlaces);
            optimalTree[i] = testOptimalBST(test[i], toSearch, filePathData, optimalOrder);
        }

        System.out.printf("%-8s %12s %12s %14s %14s%n", 
                "N", "Array", "BST(as-is)", "BST(sorted)", "BST(optimal)");
        System.out.println("------------------------------------------------------------------");

        for (int i = 0; i < test.length; i++) {
            System.out.printf(
                "%-8d %12.1f %12.1f %14.1f %14.1f%n",
                test[i], arrValues[i], asIsTree[i], sortedTree[i], optimalTree[i]);
        }
   }

    /**
     * Measures the average number of comparisons required to search for the
     * provided place names in an array-backed data structure.
     *
     * @param N the number of place records to load
     * @param search the place names to search for
     * @param data the path to the input data file
     * @return the average number of search comparisons across 50 searches
     */
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

    /**
     * Measures the average number of comparisons required to search for the
     * provided place names in a binary search tree built from records loaded in
     * their original order.
     *
     * @param N the number of place records to load
     * @param search the place names to search for
     * @param data the path to the input data file
     * @return the average number of search comparisons across 50 searches
     */
    static double testBST(int N, String[] search, String data) {
        int total = 0;
        
        PlaceNameArray places = new PlaceNameArray(N);
        places.loadRecords(N, data);
        
        PlaceNameBST tree = new PlaceNameBST(N, places);

        for (int i = 0; i < 50; i++) {
            tree.findPlace(search[i]);
            total += tree.getSearchComparisons();
        }
        return total / 50.0;
    }
    
    /**
     * Measures the average number of comparisons required to search for the
     * provided place names in a binary search tree built from pre-sorted place
     * data.
     *
     * @param N the number of place records to include in the tree
     * @param search the place names to search for
     * @param data the sorted place data used to build the tree
     * @return the average number of search comparisons across 50 searches
     */
    static double testSortedBST(int N, String[] search, PlaceNameArray data) {
        int total = 0;
        
        PlaceNameBST tree = new PlaceNameBST(N, data);

        for (int i = 0; i < 50; i++) {
            tree.findPlace(search[i]);
            total += tree.getSearchComparisons();
        }
        return total / 50.0;
    }
    
    /**
     * Measures the average number of comparisons required to search for the
     * provided place names in a binary search tree built from pre-sorted place
     * data.
     *
     * @param N the number of place records to include in the tree
     * @param search the place names to search for
     * @param data the sorted place data used to build the tree
     * @return the average number of search comparisons across 50 searches
     */
    static double testOptimalBST(int N, String[] search, String data, String[] order) {
        int total = 0;
        
        PlaceNameArray places = new PlaceNameArray(14000);
        places.loadRecords(14000, data);

        PlaceNameArray optimalPlaces = new PlaceNameArray(N);
        int index = 0;

        while (optimalPlaces.getLoadedPlaces() < N && index < 12499) {
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
