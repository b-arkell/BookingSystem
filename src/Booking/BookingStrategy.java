package Booking;

import Scheduling.*;

public interface BookingStrategy {
	boolean book(String resourceId, String userId, TimeSlot slot);
}


