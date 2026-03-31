package ResourceStates;

public class AvailableState implements ResourceState{

	@Override	// changed from resource to Iresource 
	public void book(IResource resource, String userId) {
		// TODO add logic for booking resource
		resource.setState(new BookedState());
		System.out.println("Booking " + resource.getName() + " for " + userId);
	}

	@Override	// changed from resource to Iresource 
	public void release(IResource resource) {
		// TODO add logic to release a resource
		System.out.println("ResourceName is already available");
	}

}
