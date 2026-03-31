package Booking;

public class RoomBookingStrategy implements BookingStrategy{

	@Override
	public boolean book(String resourceId, String userId) {
		System.out.println("Booking study room " + resourceId 
							+ "for user " + userId + "(Placeholder amount of time)");
		
		// logic that would check if a room is available
		return true;
	}

}
