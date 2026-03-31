package Booking;

//import ResourceStates.Resource;
import ResourceStates.IResource;

public class BookingService {

	// changed from resource to Iresource 
	public void BookResource(IResource resource, String userId) {
		System.out.println("Processing booking for " + resource.getName());
		resource.book(userId);
	}

	// changed from resource to Iresource 
	public void ReleaseResource(IResource resource, String userId) {
		System.out.println("Processing release for " + resource.getName());
		resource.release(userId);
	}
	
}
