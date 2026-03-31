package Scheduling;

import java.io.File;
import java.time.LocalDateTime;
import Booking.*;
import ResourceStates.*;

public class TestSchedulingMain {

	public static void main(String[] args) throws ClassNotFoundException {
		
		BookingService bookingService = new BookingService();
		Scheduler scheduler = new Scheduler();
		
		File file = new File("scheduler.dat");
		if (!file.exists() || file.length() == 0) {
			System.out.println("No Previous Scheduler Data. Starting Fresh Schedule...");
		}else {
			scheduler.loadFromFile("scheduler.dat");			
		}
		
		
		
		// create resource
		IResource room = ResourceFactory.createResource("Study Room 101",new RoomBookingStrategy());
		
		// create time slot			// int year, int month, int dayOfMonth, int hour, int minute
		TimeSlot slot = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), "User1");
		
		
		/// USER ACTION
		
		System.out.println("___User Logged in___");
		
		scheduler.addPending(room.getName(), slot);
		room.setState(new PendingState());
		
		System.out.println("User Requested Booking for " + room.getName());
		
		
		/// Admin Action
		System.out.println("___Admin Logged in___");
		
		if (scheduler.canApprove(room.getName(), slot)){
			
			bookingService.BookResource(room, "User1");
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
        
		
	}

}
