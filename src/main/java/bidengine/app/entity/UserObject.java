package bidengine.app.entity;

import java.util.Map;

import bidengine.app.idgenerator.IdGenerator;

public class UserObject {
	
	private final String username;
	private final String password;
	private final String userid = IdGenerator.getId(UserObject.class);
	private final long creationTime = System.currentTimeMillis();
	private long lastLoginTime = creationTime;
	private long activationTime = creationTime;
	private long deActivationTime;
	private UserStatus userStatus = UserStatus.ACTIVE;
	private String token;
	public UserObject(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void updateLastLogin() {
		lastLoginTime = System.currentTimeMillis();
		userStatus = UserStatus.ACTIVE;
	}
	
	public void updateActivation() {
		activationTime = System.currentTimeMillis();
		userStatus = UserStatus.ACTIVE;
	}
	
	public void deActivateUser() {
		deActivationTime = System.currentTimeMillis();
		userStatus = UserStatus.INACTIVE;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUserid() {
		return userid;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public long getActivationTime() {
		return activationTime;
	}

	public long getDeActivationTime() {
		return deActivationTime;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public String getToken() {
		return token;
	}

	public synchronized void setTokenAndUpdate(String token, Map<String, UserObject> tokenMap) {
		this.token = token;
		if(token!=null) {
			updateLastLogin();
			tokenMap.put(token, this);
		}else {
			tokenMap.remove(token);
		}
	}

}
