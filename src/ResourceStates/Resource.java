package ResourceStates;


import Booking.BookingStrategy;

public class Resource {

	private String name;
	private ResourceState state;
	private BookingStrategy bookingStrategy;
	
	
	public Resource(String name, BookingStrategy strategy) {
		this.name = name;
		this.state = new AvailableState();
		this.bookingStrategy = strategy;
	}
	
	
	public String getName() {return name;}
	public ResourceState getState() {return state;}
	
	public void setState(ResourceState state) {
		this.state = state;
	}
	
	public BookingStrategy getBookingStrategy() {
		return bookingStrategy;
	}
	
	public void setBookingStrategy(BookingStrategy strategy) {
		this.bookingStrategy = strategy;
	}
	
	public void book(String userId) {
		state.book(this, userId);
	}
	
	public void release(String userId) {
		state.release(this);
	}
	
	
	
	
	
	
	
	void display() {
		System.out.println("Displaying Resource");
	}
}
