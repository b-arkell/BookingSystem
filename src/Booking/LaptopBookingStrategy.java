package Booking;

public class LaptopBookingStrategy implements BookingStrategy {

	@Override
	public boolean book(String resourceId, String userId) {
		System.out.println("Booking Laptop " + resourceId 
							+ "for user " + userId + "(Placeholder amount of time)");

		// logic to check if laptop is available
		return true;
	}

}
