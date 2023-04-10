package vehicles;

import static java.lang.Math.abs;

import planning.City;

public class Plane extends Vehicle {
    private static final int speed = 900;
    public Plane(String model){
        setModel(model);
    }
    public double calculateCost(int distance) {
        return distance * 0.09 - 50;
    }
    @Override
    public int calculateDistance(City city1, City city2) {
        if ((abs(city1.getX() - city2.getX())) > 20000) {
            return (int) Math.hypot(abs(city1.getY() - city2.getY()), abs(abs(city1.getX() - city2.getX())-40000));
        } else {
            return (int) Math.hypot(abs(city1.getY() - city2.getY()), abs(city1.getX() - city2.getX()));
        }
    }

    public double calculateDuration(int distance) {
        return (double) distance / speed + 3;
    }
    public String getModel(){
        return super.getModel();
    }
    public void setModel(String model){
        super.setModel(model);
    }
    public static int getSpeed() {
        return speed;
    }
}
