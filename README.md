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
The user attempts to take control of a car every 100ms. Once a car is under control, it updates the speed of the car 3 times (2 increases then 2 decrease). The user then releases the car.

+ public synchronized boolean isControlling()
Synchronise to ensure mutual exclusion during execution. Method to determine if the user is currently controlling a car.

+ public synchronized boolean isControlling(Car x)
This function is probably not necessary. Overloaded function. It checks if the provided car is controlled by the user.
public synchronized int getId()
+ public synchronized boolean updateSpeed(int x)
Currently, the user can just speedUp or slowDown the car by 10 km/s. If the User is not controlling a car, return false. This method is a control to speed up or slow down the car. If a negative integer is input, and the user is controlling a car then the car is slowed by 10 km/s. If a positive integer is input, speed the car up by 10km/s and return true. If 0 is input, return false. 

+ public synchronized boolean releaseCar()
This is a method to release control of the car. If the user does not have control of a car, returns null. If the user does, slows the car down to 0 km/s to ensure safe release, then sets control to null. Return true. Sleeps between each slow down to prevent Busy-Wait Loop. Notifies other threads then returns true.

+ public synchronized boolean setControl()
Looks for an available car and sets the controlling variable to that car, and sets the cars controller to the user. This is done synchronously to ensure mutual exclusion and there is a 100ms wait to ensure no Busy-Wait Loop. Returns true when a car is found and false when the Thread is interupted.

+ public synchronized int getId()
Returns the id of the user.

## Car 
### Member variables
+ String brand;
+ int mileage;
+ String make;
+ int id;
+ int currentSpeed = 0;
+ User controller;

[brand, mileage, make]: Generic information for a car for practice purposes, these attributes could be used as a super key.
currentSpeed: The current speed of the car.
User Controller: The user currently controlling this car. Null if the car is available.

### Member Functions and Constructor
+ Car(String brand, int mileage, String make, int id)
Constructor. Assigns the input values to the appropriate member variables. Sets controller to null.

+ public synchronized void updateMileage(int x)
Adds the input mileage to the cars mileage.

+ public synchronized boolean speedUp()
Increases the cars speed by 10 and returns true.

+ public synchronized boolean slowDown()
Decreases the speed of the car and return true. If the speed is 10 or less, sets speed to 0 and returns true.

+ public synchronized void setController(User user)
Sets the input user to the controller member variable. This method also unassigns a user from the car.

+ public synchronized boolean isAvailable()
Checks if there is a user controlling the car. If controller is null, return true to signify that the car is availablr, otherwise return false.

+ public synchronized int getCurrentSpeed()
Returns currentSpeed variable

+ public synchronized int getId()
Returns the cars' id variable

+ public synchronized User getController()
Returns the controller variable

## Global

### Member variables
+ public static Car[] cars
+ public static User[] users

This is the public arrays of users and cars used in main and User.run().

## Main
Initializes the global users and cars arrays, and then runs each user as a thread.

# Concepts Learnt:
+ Implements Runnable --> Add to class definition to include concurrent functionality.
+ Adding a run() function to my class that will be running concurrently. Parameters must be empty.
+ Adding synchronized to the function signature ensures the function accessing resources mutually exclusively.
+ It may be good design for both objects to store the control. I.e the User should know which Car is controlling it and the Car should know which user is controlling it.
+ Git and GitHub is not the same thing. Git is a local software. GitHub is a system which uses Git and is popular in the industry.
+ If a message for a commit is not provided, it will not be performed by vscode Git and GitHub extentions.
+ Busy-Wait Loop/Spin-Wait Loop: When a condition is run continuously without pausing, using CPU cycles unneccessarily. Good practice to avoid this.
+ You can declare a sycnronize function which is used to ensure mutual exclusion when working with shared resources.

# Conclusion
Multiple tests will be performed to test if any properties of concurrency are violated leading to deadlock, starvation, etc. Checks if Mutex is successfully implemented. Multiple tests since the output is expected to be slightly different every run.

## Output Tests
+ ### Test 1
User 0 is controlling Car 1
Car 1 is being controlled by 0
User 0 increased speed of Car 1
User 0 increased speed of Car 1
User 0 decreased speed of Car 1
User 0 decreased speed of Car 1
User 3 is controlling Car 2
Car 2 is being controlled by 3
User 3 increased speed of Car 2
User 3 increased speed of Car 2
User 3 decreased speed of Car 2
User 3 decreased speed of Car 2
User 1 is controlling Car 3
Car 3 is being controlled by 1
User 1 increased speed of Car 3
User 1 increased speed of Car 3
User 1 decreased speed of Car 3
User 1 decreased speed of Car 3
User 0 released control of car 1
User 3 released control of car 2
User 1 released control of car 3
Car 2 is unassigned 
Car 1 is unassigned 
Car 3 is unassigned 
User 2 is controlling Car 1
Car 1 is being controlled by 2
User 2 increased speed of Car 1
User 2 increased speed of Car 1
User 2 decreased speed of Car 1
User 2 decreased speed of Car 1
User 2 released control of car 1
Car 1 is unassigned 

+ ### Test 2
User 0 is controlling Car 1
Car 1 is being controlled by 0
User 0 increased speed of Car 1
User 0 increased speed of Car 1
User 0 decreased speed of Car 1
User 0 decreased speed of Car 1
User 3 is controlling Car 2
Car 2 is being controlled by 3
User 3 increased speed of Car 2
User 3 increased speed of Car 2
User 3 decreased speed of Car 2
User 3 decreased speed of Car 2
User 1 is controlling Car 3
Car 3 is being controlled by 1
User 1 increased speed of Car 3
User 1 increased speed of Car 3
User 1 decreased speed of Car 3
User 1 decreased speed of Car 3
User 3 released control of car 2
User 0 released control of car 1
User 1 released control of car 3
Car 2 is unassigned 
Car 1 is unassigned 
Car 3 is unassigned 
User 2 is controlling Car 1
Car 1 is being controlled by 2
User 2 increased speed of Car 1
User 2 increased speed of Car 1
User 2 decreased speed of Car 1
User 2 decreased speed of Car 1
User 2 released control of car 1
Car 1 is unassigned

+ ### Test 3
User 0 is controlling Car 1
Car 1 is being controlled by 0
User 0 increased speed of Car 1
User 0 increased speed of Car 1
User 0 decreased speed of Car 1
User 0 decreased speed of Car 1
User 3 is controlling Car 2
Car 2 is being controlled by 3
User 3 increased speed of Car 2
User 3 increased speed of Car 2
User 3 decreased speed of Car 2+ ### Test 1
User 0 is controlling Car 1
Car 1 is being controlled by 0
User 0 increased speed of Car 1
User 0 increased speed of Car 1
User 0 decreased speed of Car 1
User 0 decreased speed of Car 1
User 3 is controlling Car 2
Car 2 is being controlled by 3
User 3 increased speed of Car 2
User 3 increased speed of Car 2
User 3 decreased speed of Car 2
User 3 decreased speed of Car 2
User 1 is controlling Car 3
Car 3 is being controlled by 1
User 1 increased speed of Car 3
User 1 increased speed of Car 3
User 1 decreased speed of Car 3
User 1 decreased speed of Car 3
User 0 released control of car 1
User 3 released control of car 2
User 1 released control of car 3
Car 2 is unassigned 
Car 1 is unassigned 
Car 3 is unassigned 
User 2 is controlling Car 1
Car 1 is being controlled by 2
User 2 increased speed of Car 1
User 2 increased speed of Car 1
User 2 decreased speed of Car 1
User 2 decreased speed of Car 1
User 2 released control of car 1
Car 1 is unassigned 

+ ### Test 2
User 0 is controlling Car 1
Car 1 is being controlled by 0
User 0 increased speed of Car 1
User 0 increased speed of Car 1
User 0 decreased speed of Car 1
User 0 decreased speed of Car 1
User 3 is controlling Car 2
Car 2 is being controlled by 3
User 3 increased speed of Car 2
User 3 increased speed of Car 2
User 3 decreased speed of Car 2
User 3 decreased speed of Car 2
User 1 is controlling Car 3
Car 3 is being controlled by 1
User 1 increased speed of Car 3
User 1 increased speed of Car 3
User 1 decreased speed of Car 3
User 1 decreased speed of Car 3
User 3 released control of car 2
User 0 released control of car 1
User 1 released control of car 3
Car 2 is unassigned 
Car 1 is unassigned 
Car 3 is unassigned 
User 2 is controlling Car 1
Car 1 is being controlled by 2
User 2 increased speed of Car 1
User 2 increased speed of Car 1
User 2 decreased speed of Car 1
User 2 decreased speed of Car 1
User 2 released control of car 1
Car 1 is unassigned

+ ### Test 3
User 0 is controlling Car 1
Car 1 is being controlled by 0
User 0 increased speed of Car 1
User 0 increased speed of Car 1
User 0 decreased speed of Car 1
User 0 decreased speed of Car 1
User 3 is controlling Car 2
Car 2 is being controlled by 3
User 3 increased speed of Car 2
User 3 increased speed of Car 2
User 3 decreased speed of Car 2
User 3 decreased speed of Car 2
User 1 is controlling Car 3
Car 3 is being controlled by 1
User 1 increased speed of Car 3
User 1 increased speed of Car 3
User 1 decreased speed of Car 3
User 1 decreased speed of Car 3
User 0 released control of car 1
User 1 released control of car 3
User 3 released control of car 2
Car 3 is unassigned 
Car 2 is unassigned 
Car 1 is unassigned 
User 2 is controlling Car 1
Car 1 is being controlled by 2
User 2 increased speed of Car 1
User 2 increased speed of Car 1
User 2 decreased speed of Car 1
User 2 decreased speed of Car 1
User 2 released control of car 1
Car 1 is unassigned
User 3 decreased speed of Car 2
User 1 is controlling Car 3
Car 3 is being controlled by 1
User 1 increased speed of Car 3
User 1 increased speed of Car 3
User 1 decreased speed of Car 3
User 1 decreased speed of Car 3
User 0 released control of car 1
User 1 released control of car 3
User 3 released control of car 2
Car 3 is unassigned 
Car 2 is unassigned 
Car 1 is unassigned 
User 2 is controlling Car 1
Car 1 is being controlled by 2
User 2 increased speed of Car 1
User 2 increased speed of Car 1
User 2 decreased speed of Car 1
User 2 decreased speed of Car 1
User 2 released control of car 1
Car 1 is unassigned 

Outcome of Tests: The order of some events differ between tests, however no user tries to access a car that is under control, and a user will wait for an available car before running. Success.


