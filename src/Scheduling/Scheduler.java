package Scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scheduler {

	private Map<String, List<TimeSlot>> approved = new HashMap<>();
	private Map<String, List<TimeSlot>> pending = new HashMap<>();	
	
	// User creates request -> adds to pending
	public void addPending(String resourceId, TimeSlot slot) {
		pending.computeIfAbsent(resourceId, k -> new ArrayList<>()).add(slot);
	}
	
	// Admin checks conflicts against approved list
	public boolean canApprove(String resourceId, TimeSlot slot) {
		List<TimeSlot> slots = approved.getOrDefault(resourceId, new ArrayList<>());
		
		for (TimeSlot s : slots) {
			if (s.conflictsWith(slot)) {
				return false;
			}
		}
		return true;
	}
	
	// move from pending to approved
	public void approve(String resourceId, TimeSlot slot) {
		approved.computeIfAbsent(resourceId,  k -> new ArrayList<>()).add(slot);
		pending.getOrDefault(resourceId, new ArrayList<>()).remove(slot);
	}
	
	// remove from pending/reject
	public void reject(String resourceId, TimeSlot slot) {
		pending.getOrDefault(resourceId, new ArrayList<>()).remove(slot);
	}
	
	
}
