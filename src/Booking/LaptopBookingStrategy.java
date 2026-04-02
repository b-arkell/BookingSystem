package Booking;

import Scheduling.*;

public class LaptopBookingStrategy implements BookingStrategy {

	private Scheduler scheduler;
	
	@Override
	public boolean book(String resourceId, String userId, TimeSlot slot) {
		System.out.println("Request for: " + resourceId 
				+ " for user " + userId + " Timeslot: Start= " + slot.getStart() + " End= " + slot.getEnd());

		scheduler.addPending(resourceId, slot);
		return true;
	}

}
