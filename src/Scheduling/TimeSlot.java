package Scheduling;

import java.time.LocalDateTime;

public class TimeSlot {

	private LocalDateTime start;
	private LocalDateTime end;
	
	
	public TimeSlot(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}
	
	
	public LocalDateTime getStart() { return start; }
	public LocalDateTime getEnd() {return end; }
	
	
	public boolean conflictsWith(TimeSlot otherSlot) {
		return start.isBefore(otherSlot.end) && end.isAfter(otherSlot.start);
	}
}
