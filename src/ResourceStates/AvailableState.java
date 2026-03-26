package ResourceStates;

public class AvailableState implements ResourceState{

	@Override
	public void book(Resource resource, String userId) {
		// TODO add logic for booking resource
		resource.setState(new BookedState());
		System.out.println("Booking " + resource.getName() + " for " + userId);
	}

	@Override
	public void release(Resource resource) {
		// TODO add logic to release a resource
		System.out.println("ResourceName is already available");
	}

}
