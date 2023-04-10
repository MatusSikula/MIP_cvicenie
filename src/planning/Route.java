package planning;
import vehicles.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Route {
    private ArrayList<City> cities = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    private City start;
    private int totalDistance = 0;

    public Route() {
    }

    public void eastSort() {
        int number = 0;
        Comparator<City> comparator = Comparator.comparingInt(City -> City.getX());
        this.cities.sort(comparator);
        ArrayList<City> temp = new ArrayList<>();
        for (int i = 0; i < this.cities.size(); i++) {
            if (this.cities.get(i).getX() > start.getX()) {
                temp.add(this.cities.get(i));
                number++;
            }
        }
        for (int i = 0; i < this.cities.size() - number; i++) {
            temp.add(this.cities.get(i));
        }
        this.cities = temp;
    }

    public void westSort() {
        int number = 0;
        Comparator<City> comparator = Comparator.comparingInt(City -> City.getX());
        comparator = comparator.reversed();
        this.cities.sort(comparator);
        ArrayList<City> temp = new ArrayList<>();
        for (int i = 0; i < this.cities.size(); i++) {
            if (this.cities.get(i).getX() < start.getX()) {
                temp.add(this.cities.get(i));
                number++;
            }
        }
        for (int i = 0; i < this.cities.size() - number; i++) {
            temp.add(this.cities.get(i));
        }
        this.cities = temp;
    }

    public void addCity(City... args) {
        this.cities.addAll(Arrays.asList(args));
    }

    public void setVehicles(Plane plane, Car car) {
        this.vehicles.clear();
        if (this.start.getCountry().getContinent() == this.cities.get(0).getCountry().getContinent()) {
            this.vehicles.add(car);
        } else {
            this.vehicles.add(plane);
        }
        for(int i=1;i<this.cities.size();i++){
            if(this.cities.get(i-1).getCountry().getContinent() == this.cities.get(i).getCountry().getContinent()){
                this.vehicles.add(car);
            }
            else{
                this.vehicles.add(plane);
            }
        }
        if(this.start.getCountry().getContinent()==this.cities.get(this.cities.size()-1).getCountry().getContinent()){
            this.vehicles.add(car);
        }else{
            this.vehicles.add(plane);
        }
    }
    public ArrayList<Vehicle> getVehicles(){
        return this.vehicles;
    }

    public int getTotalDistance() {

        return totalDistance;
    }

    public City getStart() {
        return this.start;
    }

    public void setStart(City e) {
        start = e;
    }

    public ArrayList<City> getCities() {
        return this.cities;
    }

    public void planeAdjust(Car car) {

        if(start.getNumberOfAirports()==0 && this.vehicles.get(0) instanceof Plane){
            this.cities.add(0,start.getClosestAirport());
            this.vehicles.add(0, car);
        }
        for (int i = 0; i < this.cities.size(); i++) {
            if(this.cities.get(i).getNumberOfAirports()==0 && this.vehicles.get(i) instanceof Plane ){
                this.cities.add(i,cities.get(i).getClosestAirport());
                i++;
                this.vehicles.add(i,car);
            }
            if(this.cities.get(i).getNumberOfAirports()==0 && this.vehicles.get(i+1) instanceof Plane ){
                this.cities.add(i+1,cities.get(i).getClosestAirport());
                this.vehicles.add(i,car);
            }
        }

        if(start.getNumberOfAirports()==0 && this.vehicles.get(this.vehicles.size()-1) instanceof Plane){
            this.cities.add(this.cities.size(),start.getClosestAirport());
            this.vehicles.add(this.vehicles.size(),car);
        }
    }
    public void calculateDistance(){
        this.totalDistance = this.vehicles.get(0).calculateDistance(start, this.cities.get(0));
        for (int i = 1; i < this.cities.size(); i++) {
            this.totalDistance += this.vehicles.get(i).calculateDistance(this.cities.get(i - 1), this.cities.get(i));
        }
        this.totalDistance += this.vehicles.get(this.vehicles.size()-1).calculateDistance(this.cities.get(this.cities.size() - 1), start);
    }
}
