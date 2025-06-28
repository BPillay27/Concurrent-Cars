# Concurrent-Cars
+ Practice Concurrency concepts in Java. This project aims to create a system where concurrent users can access cars and control them

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
Synchornise to ensure mutual exclusion during execution. 



## Car 

## Main

# Concepts Learnt:
+ Implements Runnable --> Add to class definition to include concurrent functionality.
+ Adding a run() function to my class that will be running concurrently. Parameters must be empty.
+ Adding synchronized to the function signature ensures the function accessing resources mutually exclusively.
+ It may be good design for both objects to store the control. I.e the User should know which Car is controlling it and the Car should know which user is controlling it.

# Conclusion


