package ResourceStates;

public class PendingState implements ResourceState{

	@Override
	public void book(IResource resource, String userId) {
		System.out.println("Request is Pending Admin Approval.");
	}

	@Override
	public void release(IResource resource) {
		System.out.println("cannot Release Resource. Still Pending.");		
	}

	
	
}
