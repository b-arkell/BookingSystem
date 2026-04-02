package ResourceStates;
import Booking.BookingStrategy;
import Scheduling.*;
//import java.io.Serializable;

public interface IResource {
	public String getName();
	public ResourceState getState();
	public void setState(ResourceState state);
	public BookingStrategy getBookingStrategy();
	public void setBookingStrategy(BookingStrategy strategy);
	public void book(String userId, TimeSlot slot);
	public void release(String userId);
	public void display();
}
