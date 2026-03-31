package Scheduling;

import java.time.LocalDateTime;
import java.io.Serializable;

public class TimeSlot implements Serializable{
	private static final long serialVersionUID = 1L;

	private LocalDateTime start;
	private LocalDateTime end;
	private String userId;
	
	
	public TimeSlot(LocalDateTime start, LocalDateTime end, String userId) {
		this.start = start;
		this.end = end;
		this.userId = userId;
	}
	
	
	public LocalDateTime getStart() { return start; }
	public LocalDateTime getEnd() { return end; }
	public String getUserId() { return userId; }
	
	
	public boolean conflictsWith(TimeSlot otherSlot) {
		return start.isBefore(otherSlot.end) && end.isAfter(otherSlot.start);
	}
	
	@Override
	public String toString() {
		return start + " -> " + end + "(UserId: " + userId + ")"; 
	}
}
