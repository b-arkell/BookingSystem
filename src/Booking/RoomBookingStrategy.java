package Booking;

import java.io.Serializable;

public class RoomBookingStrategy implements BookingStrategy, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean book(String resourceId, String userId) {
		System.out.println("Booking study room " + resourceId 
							+ "for user " + userId + "(Placeholder amount of time)");
		
		// logic that would check if a room is available
		return true;
	}

}
