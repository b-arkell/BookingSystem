package Booking;

import java.io.Serializable;
import Scheduling.*;

public class RoomBookingStrategy implements BookingStrategy, Serializable{
	private static final long serialVersionUID = 1L;
	private Scheduler scheduler;
	
	public RoomBookingStrategy(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	@Override
	public boolean book(String resourceId, String userId, TimeSlot slot) {
		System.out.println("Request for: " + resourceId 
							+ " for user " + userId + " Timeslot: Start= " + slot.getStart() + " End= " + slot.getEnd());
		
		scheduler.addPending(resourceId, slot);
		return true;
	}// can return true/false if moving to an automated system 

}
