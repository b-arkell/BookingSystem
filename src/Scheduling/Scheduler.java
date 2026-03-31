package Scheduling;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Scheduler implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Map<String, List<TimeSlot>> approved = new HashMap<>();
	private Map<String, List<TimeSlot>> pending = new HashMap<>();	
	
	// User creates request -> adds to pending
	public void addPending(String resourceId, TimeSlot slot) {
		System.out.println("[Scheduler] Adding a pending resource for user: " + slot.getUserId() + " From: " + slot.getStart() + " To: " + slot.getEnd());
		pending.computeIfAbsent(resourceId, k -> new ArrayList<>()).add(slot);
	}
	
	// Admin checks conflicts against approved list
	public boolean canApprove(String resourceId, TimeSlot slot) {
		List<TimeSlot> slots = approved.getOrDefault(resourceId, new ArrayList<>());
		
		for (TimeSlot s : slots) {
			if (s.conflictsWith(slot)) {
				System.out.println("[Scheduler] Resource: " + resourceId + " Has Overlap.");
				return false;
			}
		}
		System.out.println("[Scheduler] Resource: " + resourceId + " Has no Overlap.");
		return true;
	}
	
	// move from pending to approved
	public void approve(String resourceId, TimeSlot slot) {
		approved.computeIfAbsent(resourceId,  k -> new ArrayList<>()).add(slot);
		pending.getOrDefault(resourceId, new ArrayList<>()).remove(slot);
		System.out.println("[Scheduler] Moving resource: " + resourceId + " from Pending to Approved...");
	}
	
	// remove from pending/reject
	public void reject(String resourceId, TimeSlot slot) {
		pending.getOrDefault(resourceId, new ArrayList<>()).remove(slot);
		System.out.println("[Scheduler] Rejecting resource: " + resourceId + " and Removing From Pending...");
	}
	
	public void saveToFile(String filename) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
			out.writeObject(approved);
			out.writeObject(pending);
			System.out.println("[Scheduler] saving schedule to file " + filename);
		}catch (IOException e){
			System.err.println("[Scheduler] failed to save schedule to file " + filename);
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadFromFile(String filename) throws ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
			approved = (Map<String, List<TimeSlot>>) in.readObject();
			pending = (Map<String, List<TimeSlot>>) in.readObject();
			System.out.println("[Scheduler] Loading schedule from file: " + filename);
		}catch (FileNotFoundException e) {
			System.err.println("[Scheduler] Failed to find file: " + filename);
			e.printStackTrace();
		}catch (IOException | ClassNotFoundException e) {
			System.out.println("[Scheduler] IOException or Class not Found for file: " + filename);
			e.printStackTrace();
		}
	}
	
	public void clearScheduleData() throws FileNotFoundException, IOException {
		File file = new File("scheduler.dat");
		try (FileOutputStream fos = new FileOutputStream(file)){
			// writing nothing here will write an empty file to replace
			System.out.println("[Scheduler] Scheduler data cleared (file now empty).");
		} catch (IOException e) {
			System.out.println("[Scheduler] Error while clearing schedule file...");
			e.printStackTrace();
		}

	}
	
	
	
}
