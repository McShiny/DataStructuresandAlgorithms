
/**
 * Represents a single place record containing identifying data.
 * A place entry stores the place ID, place name, municipality, province,
 * and population.
 */
public class PlaceNameEntry {
    
    private int id;
    private String placeName;
    private String municipality;
    private String province;
    private int population;
    
    /**
     * Constructs a PlaceNameEntry object from individual string values.
     * Numeric values are converted to integers.
     *
     * @param id the unique identifier of the place as a string
     * @param placeName the name of the place
     * @param municipality the municipality in which the place is located
     * @param province the province in which the place is located
     * @param population the population of the place as a string
     */
    public PlaceNameEntry(String id, String placeName, String municipality, String province, String population) {

        this.id = Integer.parseInt(id);
        this.placeName = placeName;
        this.municipality = municipality;
        this.province = province;
        this.population = Integer.parseInt(population);

    }
    
    /**
     * Constructs a PlaceNameEntry object from an array of string values.
     * The array is expected to contain values in the order:
     * id, place name, municipality, province, and population.
     *
     * @param placeLine an array containing the place data fields
     */
    public PlaceNameEntry(String[] placeLine) {
        
        this.id = Integer.parseInt(placeLine[0]);
        this.placeName = placeLine[1];
        this.municipality = placeLine[2];
        this.province = placeLine[3];
        this.population = Integer.parseInt(placeLine[4]);

    }
    
    /**
     * Constructs a copy of an existing PlaceNameEntry object.
     *
     * @param place the PlaceNameEntry object to copy
     */
    public PlaceNameEntry(PlaceNameEntry place) {
        this.id = place.id;
        this.placeName = place.placeName;
        this.municipality = place.municipality;
        this.province = place.province;
        this.population = place.population;
    }
    
    /**
     * Returns the name of the place.
     *
     * @return the place name
     */
    public String getPlaceName() {
        return placeName;
    }
  
    /**
     * Returns a string representation of this place entry.
     * The returned string contains the ID, place name, municipality,
     * province, and population separated by spaces.
     *
     * @return a string representation of this place entry
     */
    @Override
    public String toString() {
        return id + " " + placeName + " " + municipality + " " + province + " " + population;
    }

}	
