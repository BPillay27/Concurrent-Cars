public class Car {
    public String brand;
    public int mileage;
    public String make;
    public int id;
    public int currentSpeed = 0;
    public boolean available = true;
    public User controller;

    Car(String brand, int mileage, String make, int id) {
        this.brand = brand;
        this.mileage = mileage;
        this.make = make;
        this.id = id;
    }

    public void updateMileage(int x) {
        mileage += x;
    }

    public boolean speedUp() {
        currentSpeed += 10;
        return true;
    }

    public boolean slowDown() {
        if (currentSpeed <= 10) {
            currentSpeed = 0;
            return true;
        } else {
            currentSpeed -= 10;
            return true;
        }
    }
}
