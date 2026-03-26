package ResourceStates;

public class BookedState implements ResourceState {

	@Override
	public void book(Resource resource, String userId) {
		System.out.println("ResourceName is already booked");
		
	}

	@Override
	public void release(Resource resource) {
		// TODO add logic to release resource
		System.out.println("ResourceName has been released");
		resource.setState(new AvailableState());
	}

}
