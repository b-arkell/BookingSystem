package ResourceStates;

import java.io.Serializable;
import Scheduling.*;

public class AvailableState implements ResourceState, Serializable{
	private static final long serialVersionUID = 1L;

	@Override	// changed from resource to Iresource 
	public void book(IResource resource, String userId, TimeSlot slot) {
		// TODO add logic for booking resource

		resource.getBookingStrategy().book(resource.getName(), userId, slot);
		
		System.out.println("Booking request submitted for " + resource.getName() + "by user: " + userId);
	}

	@Override	// changed from resource to Iresource 
	public void release(IResource resource) {
		// TODO add logic to release a resource
		System.out.println("ResourceName is already available");
	}

}
