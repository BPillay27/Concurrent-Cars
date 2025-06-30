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
        while (!setControl()) {
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
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
            try{
                Thread.sleep(100);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        synchronized (controlling){
            if(controlling.getController() != this){
                controlling=null;
                return false;
            }

            System.out.println("User " + id + " released control of car " + controlling.getId());
            controlling.setController(null);
            controlling = null;
            
        }
        
        // controlling=null;
        notifyAll();
        return true;
    }

    public synchronized boolean setControl() {
        while (controlling == null) {
            if (Global.cars == null) {
                return false;
            }

            for (int i = 0; i < Global.cars.length; i++) {
                Car car=Global.cars[i];

                synchronized (car){
                    if (car.isAvailable()) {
                    controlling =car;
                    System.out.println("User " + id + " is controlling Car " + car.getId());
                    car.setController(this);
                    return true;
                    }
                }
                
            }
            try {
                wait(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        return true;
    }

    public synchronized int getId() {
        return id;
    }
}
