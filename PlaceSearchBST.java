import java.util.Scanner;
import dataStructures.PlaceNameBST;

public class PlaceSearchBST {
    public static void main(String[] args) {
        
        PlaceNameBST data = new PlaceNameBST();

        Scanner in = new Scanner(System.in);
        
        System.out.println("What would you like to do? (L)oad (S)earch (H)elp (Q)uit");
        String userAction = in.next();
        
        while (userAction.compareTo("Q") != 0) {

            if (userAction.compareTo("L") == 0) {
                System.out.print("Please enter the path to the file: ");
                String filePath = in.next();
                
                System.out.print("Please enter how many places to load: ");
                int numPlaces = in.nextInt();

                data.loadRecords(numPlaces, filePath);

                System.out.println("\n---SA Places---");
                System.out.println(data);
            }

            if (userAction.compareTo("S") == 0) {
                System.out.print("Please enter the name of the place to find: ");
                String placeName = in.next();

                System.out.println("---Searching for: " + placeName + "---");
                System.out.println("not yet implemented");
            }

            if (userAction.compareTo("H") == 0) {
                System.out.println("Get Good");
            }
            
            System.out.println("What would you like to do? (L)oad (S)earch (H)elp (Q)uit");
            userAction = in.next();
        
        }

    }
}
