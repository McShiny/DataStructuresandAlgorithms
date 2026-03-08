import dataStructures.PlaceNameArray;
import dataStructures.PlaceNameBST;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PlaceExperiment {
    public static void main(String[] args) {
        
        int[] test = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        String filePathData = "files/SAplaceNames.csv";
        String filePathSearches = "files/SearchQueries.txt";
        String[] toSearch = new String[50];

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

        double[] arrValues = new double[test.length];
        for (int i = 0; i < test.length; i++) {
            arrValues[i] = testArray(test[i], toSearch, filePathData);
        }

        // Other Tests
        
        System.out.println("N       Array   BST(as-is)  BST(sorted)  BST(optimal)");
        for (int i = 0; i < test.length; i++) {
            System.out.printf("%s   %s\n", (i + 1) * 1000, arrValues[i]);
        }

    }

    private static double testArray(int N, String[] search, String data) {
        int total = 0;
        PlaceNameArray arr = new PlaceNameArray(N);
        arr.loadRecords(N, data);
        
        for (int i = 0; i < 50; i++) {
            arr.findPlace(search[i]);
            total += arr.getSearchComparisons();
        }
        
        return total / 50.0;
    }

    private static double[] testBST(int N) {
        return new double[3];
    }

    private static double asIsBST(int N) {
        return 1.0;
    }

    private static double sortedBST(int N) {
        return 1.0;
    }

    private static double optimalBST(int N) {
        return 1.0;
    }
}
