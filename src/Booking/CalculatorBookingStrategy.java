package Booking;

public class CalculatorBookingStrategy implements BookingStrategy {

	@Override
	public boolean book(String resourceId, String userId) {
		System.out.println("Booking Calculator " + resourceId 
							+ "for user " + userId + "(Placeholder amount of time)");
		
		// TODO add logic to check availiblity of calculator
		return true;
	}

}
