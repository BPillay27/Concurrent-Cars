public class User implements Runnable {
    String name;
    int id;
    Car controlling;

    User(String name, int id) {
        this.name = name;
        this.id = id;
        controlling = null;
    }

    public void run() {
        // code for what to do goes here.
        setControl();
        updateSpeed(1);
        updateSpeed(1);
        updateSpeed(-1);
        releaseCar();
    }

    public synchronized boolean isControlling() {
        return (controlling == null) ? false : true;
    }

    public synchronized boolean isControlling(Car x) {
        if (controlling == null) {
            return false;
        } else {
            if (x == null) {
                return false;
            } else {
                // ATP: There is a controll, check if same
                return (x.id == controlling.id) ? true : false;
            }
        }
    }

    public synchronized boolean updateSpeed(int x) {
        if (controlling == null) {
            return false;
        }

        if (x < 0) {
            return controlling.slowDown();
        } else if (x > 0) {
            return controlling.speedUp();
        } else {
            return false;
        }
    }

    public synchronized boolean releaseCar() {
        if (controlling == null) {
            return false;
        }

        while (controlling.getCurrentSpeed() != 0) {
            controlling.slowDown();
        }
        controlling.setController(null);
        controlling = null;
        return true;
    }

    public synchronized boolean setControl() {
        if (Global.cars == null) {
            return false;
        }

        for (int i = 0; i < Global.cars.length; i++) {
            if (Global.cars[i].isAvailable()) {
                controlling = Global.cars[i];
                Global.cars[i].setController(this);
                return true;
            }
        }

        return false;
    }
}
