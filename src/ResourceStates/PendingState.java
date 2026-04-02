package ResourceStates;
import Scheduling.*;

public class PendingState implements ResourceState{

	@Override
	public void book(IResource resource, String userId, TimeSlot slot) {
		System.out.println("Request is Pending Admin Approval for Timeslot: "
				+ "Start= " + slot.getStart() + "End= " + slot.getEnd());
	}

	@Override
	public void release(IResource resource) {
		System.out.println("cannot Release Resource: " + resource.getName() + 
				" Still Pending.");		
	}

	
	
}
