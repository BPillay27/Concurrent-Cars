public class Car {
    String brand;
    int mileage;
    String make;
    int id;
    int currentSpeed = 0;
    User controller;

    Car(String brand, int mileage, String make, int id) {
        this.brand = brand;
        this.mileage = mileage;
        this.make = make;
        this.id = id;
        controller = null;
    }

    public synchronized void updateMileage(int x) {
        mileage += x;
    }

    public synchronized boolean speedUp() {
        currentSpeed += 10;
        return true;
    }

    public synchronized boolean slowDown() {
        if (currentSpeed <= 10) {
            currentSpeed = 0;
            return true;
        } else {
            currentSpeed -= 10;
            return true;
        }
    }

    public synchronized void setController(User user) {
        controller = user;
    }

    public synchronized boolean isAvailable() {
        return (controller == null) ? true : false;
    }

    public synchronized int getCurrentSpeed() {
        return currentSpeed;
    }
}
