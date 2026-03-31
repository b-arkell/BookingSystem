package Booking;

import ResourceStates.Resource;

public class BookingService {

	public void BookResource(Resource resource, String userId) {
		System.out.println("Processing booking for " + resource.getName());
		resource.book(userId);
	}

	
	public void ReleaseResource(Resource resource, String userId) {
		System.out.println("Processing release for " + resource.getName());
		resource.release(userId);
	}
	
}
