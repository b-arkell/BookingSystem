package ResourceStates;

import Scheduling.*;

import Booking.BookingStrategy;
import java.io.Serializable;

public class Room implements IResource, Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private ResourceState state;
	private BookingStrategy bookingStrategy;
	
	
	
//	public Resource() {
//		this.name = "Resource";	//may have to change to room
//		this.state = new AvailableState();
//		this.bookingStrategy = new RoomBookingstrategy();
//	}
	
	public Room(String name, BookingStrategy strategy) {
		this.name = name;
		this.state = new AvailableState();
		this.bookingStrategy = strategy;
	}
	
	@Override
	public String getName() {return name;}
	
	@Override
	public ResourceState getState() {return state;}
	
	@Override
	public void setState(ResourceState state) {
		this.state = state;
	}
	
	@Override
	public BookingStrategy getBookingStrategy() {
		return bookingStrategy;
	}
	
	@Override
	public void setBookingStrategy(BookingStrategy strategy) {
		this.bookingStrategy = strategy;
	}
	
	@Override
	public void book(String userId, TimeSlot slot) {
		state.book(this, userId, slot);
	}
	
	@Override
	public void release(String userId) {
		state.release(this);
	}
	
	
	
	
	
	
	@Override	
	public void display() {
		System.out.println("Displaying Resource");
	}
}
