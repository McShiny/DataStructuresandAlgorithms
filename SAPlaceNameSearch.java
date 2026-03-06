import java.util.Scanner;
import dataStructures.PlaceNameArray;

public class SAPlaceNameSearch {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        System.out.print("Please enter the path to the file: ");
        String filePath = in.next();
        
        System.out.print("Please enter how many places to load: ");
        int numPlaces = in.nextInt();

        PlaceNameArray data = new PlaceNameArray(64);

        data.loadRecords(numPlaces, filePath);
        
        System.out.println("\n---SA Places---");
        System.out.println(data);

    }
}
