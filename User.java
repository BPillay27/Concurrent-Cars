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
                // ATP: There is a controll, and there is a input
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
            return true;
        }

        while (controlling.currentSpeed != 0) {
            controlling.slowDown();
        }

        controlling = null;
        return true;
    }

}
