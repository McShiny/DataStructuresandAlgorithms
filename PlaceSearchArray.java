import java.util.Scanner;
import dataStructures.PlaceNameArray;

public class PlaceSearchArray {
    public static void main(String[] args) {
        
        PlaceNameArray data = new PlaceNameArray(1);

        Scanner in = new Scanner(System.in);
        
        System.out.println("What would you like to do? (L)oad (S)earch (H)elp (Q)uit");
        String userAction = in.next();
        in.nextLine();

        while (userAction.compareTo("Q") != 0 && userAction.compareTo("q") != 0 && userAction.compareTo("quit") != 0 && userAction.compareTo("Quit") != 0) {

            if (userAction.compareTo("L") == 0 || userAction.compareTo("l") == 0 || userAction.compareTo("load") == 0 || userAction.compareTo("Load") == 0) {
                System.out.print("Please enter the path to the file: ");
                String filePath = in.next();
                
                System.out.print("Please enter how many places to load: ");
                int numPlaces = in.nextInt();

                data.loadRecords(numPlaces, filePath);

                System.out.println(numPlaces + " places loaded.");
            }

            if (userAction.compareTo("S") == 0 || userAction.compareTo("s") == 0 || userAction.compareTo("search") == 0 || userAction.compareTo("Search") == 0) {
                System.out.print("Please enter the name of the place to find: ");
                String placeName = in.nextLine();

                System.out.println("---Searching for: " + placeName + "---");
                System.out.println(data.findPlace(placeName));
            }

            if (userAction.compareTo("H") == 0 || userAction.compareTo("h") == 0 || userAction.compareTo("help") == 0 || userAction.compareTo("Help") == 0) {
                System.out.println("- PlaceSearchArray:\nProgram allows the user to dynamically load Place data into an array\nand then lets the user to search the array for additional information about a place after providing the name");
            }
            
            System.out.println("What would you like to do? (L)oad (S)earch (H)elp (Q)uit");
            userAction = in.next();
            in.nextLine();
        }

    }
}
