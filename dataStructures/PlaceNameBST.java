package dataStructures;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlaceNameBST {
    
    private Node root;
    private String[] fileInputOrder;
    int size = 0;

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

    public void loadRecords(int maxRecords, String filePath) {
        // load records to a maximum of N
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
        if (place.placeName.compareTo(node.data.placeName) < 0) {
            if (node.left == null) {
                node.left = new Node(place);
                size++;
            }
            else
                insertNode(place, node.left);
        } else if (place.placeName.compareTo(node.data.placeName) > 0) {
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

