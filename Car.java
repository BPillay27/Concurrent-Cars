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
        System.out.println("User " + controller.getId() + " increased speed of Car " + String.valueOf(id));
        updateMileage(5);

        currentSpeed += 10;
        return true;
    }

    public synchronized boolean slowDown() {
        System.out.println("User " + controller.getId() + " decreased speed of Car " + String.valueOf(id));
        updateMileage(5);

        if (currentSpeed <= 10) {
            currentSpeed = 0;
            return true;
        } else {
            currentSpeed -= 10;
            return true;
        }
    }

    public synchronized void setController(User user) {
        System.out.println("Car " + String.valueOf(id) + " is being controlled by " + user.getId());
        controller = user;
    }

    public synchronized boolean isAvailable() {
        return (controller == null) ? true : false;
    }

    public synchronized int getCurrentSpeed() {
        return currentSpeed;
    }

    public synchronized int getId() {
        return id;
    }
}
