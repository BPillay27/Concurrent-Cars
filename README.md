# Concurrent-Cars
+ Practice Concurrency concepts in Java. This project aims to create a system where concurrent users can access cars and control them.
+ Pracitice Git, GitHub and their integration with VSCode.

# Design
## User
### Member variables
+ String name
+ int id
+ Car controlling

"name": The users name.
"id": This is the users id number which could be used together with their name as a super key in a database.
"controlling": This is the car currently controlled by the user. Will be null if the user is currently not controlling the car.

### Member Functions and Constructor
+ User(String name, int id)
This is the class constructor. Assigns the input name and id the objects' name and id member variables and sets controlling to null.

+ public void run()
Currently uncertain how to use run and if it should be synchronise.

+ public synchronized boolean isControlling()
Synchronise to ensure mutual exclusion during execution. Method to determine if the user is currently controlling a car.

+ public synchronized boolean isControlling(Car x)
This function is probably not necessary. Overloaded function. It checks if the provided car is controlled by the user.

+ public synchronized boolean updateSpeed(int x)
Currently, the user can just speedUp or slowDown the car by 10 km/s. If the User is not controlling a car, return false. This method is a control to speed up or slow down the car. If a negative integer is input, and the user is controlling a car then the car is slowed by 10 km/s. If a positive integer is input, speed the car up by 10km/s and return true. If 0 is input, return false. 

+ public synchronized boolean releaseCar()
This is a method to release control of the car. If the user does not have control of a car, returns null. If the user does, slows the car down to 0 km/s to ensure safe release, then sets control to null. Return true.

## Car 
### Member variables
+ String brand;
+ int mileage;
+ String make;
+ int id;
+ int currentSpeed = 0;
+ boolean available = true;
+ User controller;

[brand, mileage, make]: Generic information for a car for practice purposes, these attributes could be used as a super key.
currentSpeed: The current speed of the car.
User Controller: The user currently controlling this car. Null if the car is available.

### Member Functions and Constructor
+ Car(String brand, int mileage, String make, int id)
Constructor. Assigns the input values to the appropriate member variables. Sets controller to null.

+ public void updateMileage(int x)


+ public boolean speedUp()


+ public boolean slowDown()


## Main

# Concepts Learnt:
+ Implements Runnable --> Add to class definition to include concurrent functionality.
+ Adding a run() function to my class that will be running concurrently. Parameters must be empty.
+ Adding synchronized to the function signature ensures the function accessing resources mutually exclusively.
+ It may be good design for both objects to store the control. I.e the User should know which Car is controlling it and the Car should know which user is controlling it.
+ Git and GitHub is not the same thing. Git is a local software. GitHub is a system which uses Git and is popular in the industry.

# Conclusion


