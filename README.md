# BookingSystem
java final project


### Menu ###

Our menu is designed to display the workflow as instructed in the Phase 1 feedback:
Draft → Pending → Approved → Scheduled → Completed → Cancelled.

In the future, we would write a menu that allowed actions by a user and separately action by an admin.
The menu offers 5 choices:
0 - Clears all file contents for a clean run
1 - Runs the first part of the demo: User1 logs in and makes some drafts and requests for resources. Then the admin logs in and checks if the items in the pending listed (requested by users) are able to be approved, then either approves or rejects them.
2 - User2 logs in and makes some more resource requests, one of which has an intentional conflict with one of User1's resource requests, and is rejected by the admin.
3 - Saves user and scheduler info to file, then exits program
4 - Exit the program without saving to file.


### Patterns ###

Factory:
The factory pattern is instantiated by the following classes:
- IResource: an interface that is implemented by the various resources (Romm, Laptop..)
- ResourceFactory: The concrete class that abstracts away the if-else ladder
- The concrete classes that the factory actually builds

State Pattern

The State Pattern is used to represent and manage the different states a resource can be in, allowing its behavior to change dynamically based on its current state.

Components
ResourceState (Interface)
Defines the common behavior for all possible resource states.
Concrete States
AvailableState
BookedState
PendingState

How It Works

Each resource maintains a reference to a ResourceState object that represents its current state. Instead of using conditional logic (e.g., if or switch statements), state-specific behavior is delegated to the corresponding state class.

This design makes it easy to:

Add new states without modifying existing logic
Keep state-related behavior organized and maintainable
Ensure each state encapsulates its own rules and transitions

Strategy Pattern

The Strategy Pattern is used to define a family of booking behaviors, allowing different booking logic to be selected and applied at runtime.

Components
BookingStrategy (Interface)
Defines a common method (e.g., book()) that all booking strategies must implement.
Concrete Strategies
RoomBookingStrategy
LaptopBookingStrategy
CalculatorStrategy

How It Works

Each strategy provides its own implementation of the book() method. While the current differences mainly involve how booking information is displayed, this structure allows for much more flexibility.

This design is highly scalable and makes it easy to:

Introduce new booking rules without changing existing code
Customize behavior for different resource types
Integrate with additional systems (e.g., calendar services, scheduling APIs) in the future


