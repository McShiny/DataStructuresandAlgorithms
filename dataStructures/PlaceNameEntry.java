package dataStructures;

public class PlaceNameEntry {
    
    public int id;
    public String placeName;
    public String municipality;
    public String province;
    public int population;

    public PlaceNameEntry(String id, String placeName, String municipality, String province, String population) {

        this.id = Integer.parseInt(id);
        this.placeName = placeName;
        this.municipality = municipality;
        this.province = province;
        this.population = Integer.parseInt(population);

    }
    
    public PlaceNameEntry(String[] placeLine) {
        
        id = Integer.parseInt(placeLine[0]);
        placeName = placeLine[1];
        municipality = placeLine[2];
        province = placeLine[3];
        population = Integer.parseInt(placeLine[4]);

    }

    public String toString() {
        return id + " " + placeName + " " + municipality + " " + province + " " + population;
    }

}	
