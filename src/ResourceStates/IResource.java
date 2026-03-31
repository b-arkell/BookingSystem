package ResourceStates;
import Booking.BookingStrategy;

public interface IResource {
	public String getName();
	public ResourceState getState();
	public void setState(ResourceState state);
	public BookingStrategy getBookingStrategy();
	public void setBookingStrategy(BookingStrategy strategy);
	public void book(String userId);
	public void release(String userId);
	public void display();
}
