package Booking;
import Scheduling.*;
import Users.User;
import ResourceStates.*;
import java.time.LocalDateTime;

public class TestMain {

	public static void main(String[] args) {
		BookingService bookingService = new BookingService();
		Scheduler scheduler = new Scheduler();
		
		
		User user1 = new User();
		user1.setName("Brodie");
		user1.setUserID("001");
		

		User user2 = new User();
		user2.setName("BingBong");
		user2.setUserID("002");
		
		// user1 books room 1
		TimeSlot timeslot1 = new TimeSlot(LocalDateTime.of(2026,4, 1, 10, 0), LocalDateTime.of(2026,  4, 1, 12, 0), user1.getUserID());
		IResource room1 = ResourceFactory.createResource("Study Room 1", new RoomBookingStrategy(scheduler));
		
		// user2 books room 2
		IResource room2 = ResourceFactory.createResource("Study Room 2", new RoomBookingStrategy(scheduler));
		TimeSlot timeslot2 = new TimeSlot(LocalDateTime.of(2026,5, 2, 10, 0), LocalDateTime.of(2026,  6, 2, 12, 0), user1.getUserID());
		
		// user3 attempt book room 1 same time
		
		// user1 books room 1
		bookingService.BookResource(room1, user1.getUserID(), timeslot1);
		
		bookingService.BookResource(room2, user2.getUserID(), timeslot2);
		
		bookingService.BookResource(room1, user2.getUserID(), timeslot1);
		
		
		/// Admin login 
		
		
		// attempt approve user 1 first booking
		if (scheduler.canApprove(room1.getName(), timeslot1)) {
			scheduler.approve(room1.getName(), timeslot1);
			room1.setState(new BookedState());
		}else {
			scheduler.reject(room1.getName(), timeslot1);
			
		}
		
		
		// admin reject user 2 first booking
		scheduler.reject(room2.getName(), timeslot2);
		
		// admin attempt approve user 2 second booking
		if (scheduler.canApprove(room1.getName(), timeslot1)) {
			scheduler.approve(room1.getName(), timeslot1);
		}else {
			scheduler.reject(room1.getName(), timeslot1);
			
		}
		
		
		
		
	}

}
