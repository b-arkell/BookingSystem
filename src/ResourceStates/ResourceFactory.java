package ResourceStates;

import Booking.BookingStrategy;

public class ResourceFactory {
	
	public static IResource createResource(String type, BookingStrategy strategy) {
		
		String checkStr = type.toLowerCase();
		
		if(checkStr.contains("resource"))	// will have to change to room later
			return new Resource(type, strategy);
		if(checkStr.contains("room"))
			return new Room(type, strategy);
		if(checkStr.contains("laptop"))
			return new Laptop(type, strategy);
		
		throw new IllegalArgumentException("[ResourceFactory] Unkown resource type.");
	}
}
