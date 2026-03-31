package Users;

import java.util.LinkedHashMap;

public class User {
	
	private String userID = "NA";
	private String name = "NA";
	
	// Dictionary for Drafts
	private LinkedHashMap<String, String> Drafts = new LinkedHashMap<>();

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

	public LinkedHashMap<String, String> getDrafts() {
		return Drafts;
	}

	public void setDrafts(LinkedHashMap<String, String> drafts) {
		Drafts = drafts;
	}
	
	
}
