package Scheduling;

import java.io.File;
import java.time.LocalDateTime;
import Booking.*;
import ResourceStates.*;
import Users.User;

public class TestSchedulingMain {

	public static void main(String[] args) throws ClassNotFoundException {
		
		BookingService bookingService = new BookingService();
		Scheduler scheduler = new Scheduler();
		User user1 = new User();
		
		
		File file = new File("scheduler.dat");
		if (!file.exists() || file.length() == 0) {
			System.out.println("No Previous Scheduler Data. Starting Fresh Schedule...");
		}else {
			scheduler.loadFromFile("scheduler.dat");			
		}
		
		File userFile = new File("user1.dat");
		if (!userFile.exists() || userFile.length() == 0) {
			System.out.println("No Previous Scheduler Data. Starting Fresh Schedule...");
			user1.setUserID("007");
			user1.setName("Samwise");
		}else {
			user1.loadFromFile("user1.dat");			
		}
		
		
		try {	// Sam added for manual test
			TimeSlot myTimeslot = user1.getSpecDraft("Study Room 101");
			System.out.println("[TestSchdulingMain] Resource Name: " + "Study Room 101" + " Time Slot: " + myTimeslot);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// create resource
		IResource room = ResourceFactory.createResource("Study Room 101",new RoomBookingStrategy());
		
		// create time slot			// int year, int month, int dayOfMonth, int hour, int minute
		TimeSlot slot = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user1.getName());
		
		
		/// USER ACTION
		
		System.out.println("___User Logged in: " + user1.getName() + "___");
		user1.clearDrafts();	// Sam added for manual test
		user1.setDraft(room, slot);	// Sam added for manual test
		try {	// Sam added for manual test
			TimeSlot myTimeslot = user1.getSpecDraft(room.getName());
			System.out.println("[TestSchdulingMain] Resource Name: " + room.getName() + " Time Slot: " + myTimeslot);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scheduler.addPending(room.getName(), slot);
		room.setState(new PendingState());
		
		System.out.println("User Requested Booking for " + room.getName());
		
		
		/// Admin Action
		System.out.println("___Admin Logged in___");
		
		if (scheduler.canApprove(room.getName(), slot)){
			
			bookingService.BookResource(room, user1.getName());
			scheduler.approve(room.getName(), slot);
			System.out.println("Admin approves booking...");

			

		}else {
			System.out.println("Admin rejects Booking...");
			scheduler.reject(room.getName(), slot);
			room.setState(new AvailableState());
		}
		
		// user tries again with conflict
        System.out.println("\n=== USER LOGGED IN AGAIN ===");

        TimeSlot slot2 = new TimeSlot(
                LocalDateTime.of(2026, 4, 1, 11, 0),
                LocalDateTime.of(2026, 4, 1, 13, 0), "User2"
        );

        scheduler.addPending(room.getName(), slot2);
        room.setState(new PendingState());

        System.out.println("User requested overlapping booking");

        
        /// Admin check again
        
        System.out.println("\n=== ADMIN LOGGED IN AGAIN ===");

        if (scheduler.canApprove(room.getName(), slot2)) {

            scheduler.approve(room.getName(), slot2);
            bookingService.BookResource(room, "User2");

        } else {

            System.out.println("Rejected due to conflict!");
            scheduler.reject(room.getName(), slot2);
            room.setState(new AvailableState());
        }
        
        scheduler.saveToFile("scheduler.dat");
        user1.saveToFile("user1.dat");
        
		
	}

}
