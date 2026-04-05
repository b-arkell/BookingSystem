package Users;

import ResourceStates.IResource;
import Scheduling.TimeSlot;

import java.util.LinkedHashMap;


//import java.util.List;
//import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String userID = "NA";
	private String name = "NA";
	
	// Dictionary for Drafts
	private LinkedHashMap<String, TimeSlot> Drafts = new LinkedHashMap<>();

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedHashMap<String, TimeSlot> getDrafts() {
		return Drafts;
	}
	
	public User() {
		this.userID = "NA";
		this.name = "NA";
	}
	
	public User(String userID, String name) {
		this.userID = userID;
		this.name = name;
		
		String filename = this.userID + ".dat";
		File user1File = new File(filename);
		if (!user1File.exists() || user1File.length() == 0) {
			System.out.println("No Previous User Data. Starting Fresh user file...");			
		}else {
			try {
				this.loadFromFile(filename);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
//		try {
//			this.loadFromFile();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public TimeSlot getSpecDraft(String resName) throws Exception {
		if (Drafts.containsKey(resName))
			return Drafts.get(resName);
		throw new Exception(resName + " does not exist in drafts.");	//TODO Fix it
	}

	public void setDraft(String resName, TimeSlot timeSlot) {
		if(!Drafts.containsKey(resName)) {
			Drafts.put(resName, timeSlot);
			System.out.println("[User] " + resName + " added to drafts.");
		}
		else
			System.out.println("[User] " + resName + " already in drafts");
	}
	
	public void setDraft(IResource room, TimeSlot timeSlot) {
		String roomName = room.getName();
		
		if(!Drafts.containsKey(roomName)) {
			Drafts.put(roomName, timeSlot);
			System.out.println("[User] " + roomName + " added to drafts.");
		}
		else
			System.out.println("[User] " + roomName + " already in drafts");
	}
	
	public void removeDraft(String resName) {
		if(Drafts.containsKey(resName)) {
			Drafts.remove(resName);
			System.out.println("[User] " + resName + " removed from drafts.");
		}
		else
			System.out.println("[User] " + resName + " not found in drafts");
	}
	
	public void clearDrafts() {
		Drafts.clear();
		System.out.println("[User] Drafts have been cleared.");
	}
	
	public void saveToFile(String filename) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
			out.writeObject(userID);
			out.writeObject(name);
			out.writeObject(Drafts);
			System.out.println("[User] saving schedule to file " + filename);
		}catch (IOException e){
			System.err.println("[User] failed to save schedule to file " + filename);
			e.printStackTrace();
		}
	}
	
//	@SuppressWarnings("unchecked")
//	public void loadFromFile(String filename) throws ClassNotFoundException {
//		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
//			userID = (String)in.readObject();
//			name = (String)in.readObject();
//			Drafts = (LinkedHashMap<String, TimeSlot>) in.readObject();
//			System.out.println("[User] Loading user from file: " + filename);
//		}catch (FileNotFoundException e) {
//			System.err.println("[User] Failed to find file: " + filename);
//			e.printStackTrace();
//		}catch (IOException | ClassNotFoundException e) {
//			System.out.println("[User] IOException or Class not Found for file: " + filename);
//			e.printStackTrace();
//		}
//	}
	
	@SuppressWarnings("unchecked")
	public void loadFromFile(String filename) throws ClassNotFoundException {
//		String filename = this.userID + ".dat";
//		File user1File = new File(filename);
//		if (!user1File.exists() || user1File.length() == 0) {
//			System.out.println("No Previous User Data. Starting Fresh user file...");
//			user1.setUserID("007");
//			user1.setName("Samwise");
//		}else {
//			user1.loadFromFile("user1.dat");			
//		}
		
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
			this.userID = (String)in.readObject();
			this.name = (String)in.readObject();
			this.Drafts = (LinkedHashMap<String, TimeSlot>) in.readObject();
			System.out.println("[User] Loading user from file: " + filename);
		}catch (FileNotFoundException e) {
			System.err.println("[User] Failed to find file: " + filename);
			e.printStackTrace();
		}catch (IOException | ClassNotFoundException e) {
			System.out.println("[User] IOException or Class not Found for file: " + filename);
			e.printStackTrace();
		}
	}
	
	public void clearUserData(String filename) throws FileNotFoundException, IOException {
		File file = new File(filename);
		try (FileOutputStream fos = new FileOutputStream(file)){
			// writing nothing here will write an empty file to replace
			System.out.println("[User] User data cleared (file now empty).");
		} catch (IOException e) {
			System.out.println("[User] Error while clearing schedule file...");
			e.printStackTrace();
		}

	}
	
	
	
}
