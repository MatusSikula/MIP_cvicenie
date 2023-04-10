package planning;

public class City {
    private int x, y;
    private String name;
    private int numberOfAirports;
    private Country country;
    private City closestAirport;

    public City(String name, int x, int y, int numberOfAirports) {
        setName(name);
        setX(x);
        setY(y);
        setNumberOfAirports(numberOfAirports);
    }

    public String getName() {
        return this.name;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getNumberOfAirports() {
        return this.numberOfAirports;
    }

    public Country getCountry() {
        return this.country;
    }

    public String toString() {
        return this.name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getClosestAirport() {
        return this.closestAirport;
    }

    public void setClosestAirport(City city) {
        this.closestAirport = city;
    }
    public void setName(String name) {
    	this.name=name;
    }
    public void setX(int x) {
    	this.x=x;
    }
    public void setY(int y) {
    	this.y=y;
    }
    public void setNumberOfAirports(int numberOfAirports) {
    	this.numberOfAirports=numberOfAirports;
    }

}
