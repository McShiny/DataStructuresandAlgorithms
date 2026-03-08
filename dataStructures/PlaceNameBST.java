package dataStructures;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlaceNameBST {
    
    private Node root;
    private String[] fileInputOrder;
    int size = 0;
    int searchComparisons;

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

    public PlaceNameBST () {
        this.root = null;
    }

    public PlaceNameBST (PlaceNameArray places) {
        this.root = null;

        for (int i = 0; i < places.getLoadedPlaces(); i++) {
            insertNode(places.getPlaceArray()[i]);
        }
    }

    public void loadRecords(int maxRecords, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            fileInputOrder = line.split(",");
            while ((line = reader.readLine()) != null && size < maxRecords) {
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

    public int getSearchComparisons() {
        return searchComparisons;
    }
    
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

