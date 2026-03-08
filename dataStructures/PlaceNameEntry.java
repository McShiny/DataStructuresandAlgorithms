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
        
        this.id = Integer.parseInt(placeLine[0]);
        this.placeName = placeLine[1];
        this.municipality = placeLine[2];
        this.province = placeLine[3];
        this.population = Integer.parseInt(placeLine[4]);

    }

    public PlaceNameEntry(PlaceNameEntry place) {
        this.id = place.id;
        this.placeName = place.placeName;
        this.municipality = place.municipality;
        this.province = place.province;
        this.population = place.population;
    }
    
    @Override
    public boolean equals(Object object) {
        
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        PlaceNameEntry other = (PlaceNameEntry) object;

        return this.id == other.id;

    }
    
    @Override
    public String toString() {
        return id + " " + placeName + " " + municipality + " " + province + " " + population;
    }

}	
