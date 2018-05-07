package senai.sstorage.service.models;

public class ChangeNames {
	
	private String currentPassword;
	private String freshFirstName;
	private String freshLastName;
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	public String getFreshFirstName() {
		return freshFirstName;
	}
	public void setFreshFirstName(String freshFirstName) {
		this.freshFirstName = freshFirstName;
	}
	public String getFreshLastName() {
		return freshLastName;
	}
	public void setFreshLastName(String freshLastName) {
		this.freshLastName = freshLastName;
	}

}
