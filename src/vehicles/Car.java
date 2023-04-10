package vehicles;

import static java.lang.Math.abs;

import planning.City;

public class Car extends Vehicle {
    private final double mileage;
    private static final float gasPrice = 1.5F;
    private final int numberOfSeats;
    private final static int speed = 120;

    public Car(String model, int numberOfSeats, double mileage) {
        setModel(model);
        this.numberOfSeats = numberOfSeats;
        this.mileage = mileage;
    }

    public double calculateCost(int distance) {
        return distance / this.mileage * gasPrice;
    }
    @Override
    public int calculateDistance(City city1, City city2) {
        if ((abs(city1.getX() - city2.getX())) > 20000) {
            return (int) (Math.hypot(abs(city1.getY() - city2.getY()), abs(abs(city1.getX() - city2.getX())-40000))*1.2);
        } else {
            return (int) (Math.hypot(abs(city1.getY() - city2.getY()), abs(city1.getX() - city2.getX()))*1.2);
        }
    }

    public double calculateDuration(int distance) {
        return (double) distance / speed;
    }

    public double getMileage() {
        return mileage;
    }

    public static float getGasPrice() {
        return gasPrice;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public static int getSpeed() {
        return speed;
    }
    public void setModel(String model){
        this.model=model;
    }
}
