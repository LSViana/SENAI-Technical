package wstemplate.project.authentication;

import wstemplate.project.models.UserType;

public enum Authority {
	
	NONE			(0),
	REGULAR			(1),
	ADMINISTRATOR	(2);
	
	private Integer level;

	private Authority(Integer level) {
		this.level = level;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public static Authority parseUserType(UserType userType) {
		switch(userType) {
			case ADMINISTRATOR:
				return ADMINISTRATOR;
			case REGULAR:
				return REGULAR;
			default:
				return NONE;
		}
	}

}
