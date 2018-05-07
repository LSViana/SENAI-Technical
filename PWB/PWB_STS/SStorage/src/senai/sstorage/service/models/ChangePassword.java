package senai.sstorage.service.models;

public class ChangePassword {
	
	private String currentPassword;
	private String freshPassword;
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getFreshPassword() {
		return freshPassword;
	}
	public void setFreshPassword(String freshPassword) {
		this.freshPassword = freshPassword;
	}

}
