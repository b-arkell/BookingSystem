package ResourceStates;

import Booking.RoomBookingStrategy;
import Booking.BookingService;


public class TestStateMain {

	public static void main(String[] args) {
		BookingService service = new BookingService();
		
		Resource room1 = new Resource("Study Room 101", new RoomBookingStrategy());
		
		IResource myRoom = ResourceFactory.createResource("Programming Room", new RoomBookingStrategy());
		IResource myLaptop = ResourceFactory.createResource("Gaming Laptop", new RoomBookingStrategy());
		
		service.BookResource(myRoom, "User3");
		service.BookResource(myLaptop, "User3");
		
		
		
		service.BookResource(room1, "User1");
		service.BookResource(room1, "User2"); // should block booking 
		

		service.ReleaseResource(room1, "User1");// release room1
		service.BookResource(room1, "User2");		// should be able to book now.
		
		
	}

}
