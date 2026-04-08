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

State:
- 

Strategy:
-


