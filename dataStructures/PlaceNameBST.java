package dataStructures;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Stores and manages PlaceNameEntry objects in a binary search tree.
 * This class supports loading records, inserting records, searching for places,
 * and generating an in-order string representation of the tree contents.
 */
public class PlaceNameBST {
    
    private Node root;
    private String[] fileInputOrder;
    private int size = 0;
    private int searchComparisons;

    private class Node {
        
        PlaceNameEntry data;
        Node left;
        Node right;

        Node(PlaceNameEntry data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

    }

    /**
     * Constructs an empty PlaceNameBST.
     */
    public PlaceNameBST () {
        this.root = null;
    }

    /**
     * Constructs a PlaceNameBST and inserts records from the given PlaceNameArray
     * until the requested number of records has been loaded.
     *
     * @param toLoad the number of records to insert into the tree
     * @param places the PlaceNameArray containing the source records
     */
    public PlaceNameBST (int toLoad, PlaceNameArray places) {
        this.root = null;
        int index = 0;
        while (size < toLoad && index < places.getLoadedPlaces()) {
            insertNode(places.getPlaceArray()[index]);
            index++;
        }
    }

    /**
     * Loads records from a file into the binary search tree.
     *
     * @param maxRecords the maximum number of records to load
     * @param filePath the path to the input file
     */
    public void loadRecords(int maxRecords, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            fileInputOrder = line.split(",");
            int initSize = size;
            while ((line = reader.readLine()) != null && (size - initSize) < maxRecords) {
                insertNode(new PlaceNameEntry(line.split(",")));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void insertNode(PlaceNameEntry place) {
        if (root == null) {
            root = new Node(place);
            size++;
        } else {
            insertNode(place, root);
        }
    }

    private void insertNode(PlaceNameEntry place, Node node) {
        if (node.data.placeName.compareTo(place.placeName) < 0) {
            if (node.left == null) {
                node.left = new Node(place);
                size++;
            }
            else
                insertNode(place, node.left);
        } else if (node.data.placeName.compareTo(place.placeName) > 0) {
            if (node.right == null) {
                node.right = new Node(place);
                size++;
            }
            else
                insertNode(place, node.right);
            }  
        
    }

    private ArrayList<PlaceNameEntry> inOrderTraversal(Node node, ArrayList<PlaceNameEntry> visited) {
        if (node != null) {
            inOrderTraversal(node.left, visited);
            visited.add(node.data);
            inOrderTraversal(node.right, visited);
        }

        return visited;
    }

    /**
     * Searches for a place by name and returns its string representation.
     *
     * @param placeName the name of the place to search for
     * @return the string representation of the matching place, or
     *         "Place not found in database" if no match is found
     */
    public String findPlace(String placeName) {
        searchComparisons = 0;
        if (root == null) {
            return "Place not found in database";
        }
        
        searchComparisons++;
        return findPlace(placeName, root);
    }

    private String findPlace(String placeName, Node node) {
        int comparison = node.data.placeName.compareTo(placeName);
        searchComparisons++;
        if (comparison == 0)
            return node.data.toString();
        else if (comparison < 0) {
                if (node.left != null)
                    return findPlace(placeName, node.left);
                else
                    return "Place not found in database";
        } else {
                if (node.right != null)
                    return findPlace(placeName, node.right);
                else
                    return "Place not found in databse";
        }
    }

    /**
     * Searches for a place by name and returns the matching PlaceNameEntry object.
     *
     * @param placeName the name of the place to search for
     * @param p an unused parameter used to distinguish this overloaded method
     * @return the matching PlaceNameEntry if found, otherwise null
     */
    public PlaceNameEntry findPlace(String placeName, String p) {
        searchComparisons = 0;
        if (root == null) {
            return null;
        }
        
        searchComparisons++;
        return findPlace(placeName, root, p);
    }

    private PlaceNameEntry findPlace(String placeName, Node node, String p) {
        int comparison = node.data.placeName.compareTo(placeName);
        searchComparisons++;
        if (comparison == 0)
            return node.data;
        else if (comparison < 0) {
                if (node.left != null)
                    return findPlace(placeName, node.left, p);
                else
                    return null;
        } else {
                if (node.right != null)
                    return findPlace(placeName, node.right, p);
                else
                    return null;
        }
    }
    
    /**
     * Returns the number of comparisons made during the most recent tree search.
     *
     * @return the number of search comparisons
     */
    public int getSearchComparisons() {
        return searchComparisons;
    }
    
    /**
     * Returns the number of nodes currently stored in the tree.
     *
     * @return the number of place records in the tree
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Returns a string representation of the tree contents using in-order traversal.
     *
     * @return a multi-line string containing all place records in traversal order
     */
    @Override
    public String toString() {
        String output = "";

        ArrayList<PlaceNameEntry> places = inOrderTraversal(root, new ArrayList<PlaceNameEntry>());

        for (int i = 0; i < size; i++) {
            output += places.get(i).toString() + "\n";
        }

        return output;
    }

}

