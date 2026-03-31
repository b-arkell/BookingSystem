package ResourceStates;

public class BookedState implements ResourceState {

	@Override	// changed from resource to Iresource 
	public void book(IResource resource, String userId) {
		System.out.println("ResourceName is already booked");
		
	}

	@Override	// changed from resource to Iresource 
	public void release(IResource resource) {
		// TODO add logic to release resource
		System.out.println("ResourceName has been released");
		resource.setState(new AvailableState());
	}

}
