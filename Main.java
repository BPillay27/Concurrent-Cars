//Practice idea. Remote controlled car. Need to track who has access concurrently and modify data accordingly.

import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        // define users (4)
        User[] users = new User[4];
        String[] names = new String[4];

        names[0]="Amy";
        names[1]="Howard";
        names[2]="Sheldon";
        names[3]="Penny";

        for (int i = 0; i < 4; i++) {
            users[i]=new User(names[i], i);
        }

        // define cars (3).
        Car[] cars=new Car[3];
        cars[0]=new Car("BMW",13422,"M1",1);
        cars[1]=new Car("Toyota",134222,"Corolla",2);
        cars[2]=new Car("Suzuki",5339,"Swift",3);

        
    }
}
