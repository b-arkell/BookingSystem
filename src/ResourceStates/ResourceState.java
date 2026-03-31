package ResourceStates;

public interface ResourceState {
	void book(Resource resource, String userId);
	void release(Resource resource);
}
