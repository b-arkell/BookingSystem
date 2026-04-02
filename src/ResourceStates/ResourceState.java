package ResourceStates;
import Scheduling.*;

public interface ResourceState {
	//void book(Resource resource, String userId);	// changed this from resource to iresource
	//void release(Resource resource);
	void book(IResource resource, String userId, TimeSlot slot);
	void release(IResource resource);
}
