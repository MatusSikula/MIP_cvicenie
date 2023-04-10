package vehicles;

import planning.City;

public abstract class Vehicle {
    protected String model;
    public double calculateCost(){
        return 0;
    }
    public double calculateDuration(){
        return 0;
    }
    public int calculateDistance(City city1, City city2){
        return 0;
    }
    public String toString(){
        return this.model;
    }
    public String getModel() {
    	return this.model;
    }
    public void setModel(String model) {
    	this.model=model;
    }
}
