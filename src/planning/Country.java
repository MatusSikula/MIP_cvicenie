package planning;
import java.util.ArrayList;

public class Country {
    private String name;
    private int numberOfAirports = 0;
    private String currency;
    private String continent;
    public ArrayList<City> cities = new ArrayList<>();

    public Country(String name, String currency, String continent) {
        setName(name);
        setCurrency(currency);
        setContinent(continent);
    }

    public String getName() {
        return this.name;
    }

    public String getCurrency() {
        return this.currency;
    }

    public int getNumberOfAirports() {
        return this.numberOfAirports;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setNumberOfAirports(int numberOfAirports) {
        this.numberOfAirports = numberOfAirports;
    }

    public void addCity(City city) {
        this.cities.add(city);
        city.setCountry(this);
    }

    public String toString() {
        return getName();
    }
    public String getContinent(){
        return continent;
    }
    public void setContinent(String continent) {
    	this.continent=continent;
    }
}
