package bidengine.app.services.user;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import bidengine.app.entity.UserObject;
import bidengine.app.services.user.auth.UserAuthenticationService;

@Repository
public class UserDaoImpl implements UserDao, UserAuthenticationService{
	private ConcurrentMap<String, UserObject> byId = new ConcurrentHashMap<>();
	private ConcurrentMap<String, UserObject> byName = new ConcurrentHashMap<>();
	private Map<String, UserObject> byToken = new ConcurrentHashMap<>();

	@Override
	public UserObject findByUsername(String username) {
		return byName.get(username);
	}

	@Override
	public UserObject findByUserid(String userid) {
		return byId.get(userid);
	}

	@Override
	public UserObject tryAndCreateNewUser(String username, String password) {
		if(byName.containsKey(username)) {
			throw new IllegalArgumentException("User name already exists.");
		}
		UserObject user = new UserObject(username, password);
		byId.put(user.getUserid(), user);
		byName.put(user.getUsername(), user);
		return user;
	}

	@Override
	public UserObject findByToken(String token) {
		return byToken.get(token);
	}

	@Override
	public String login(String username, String password) {
		String uuid = UUID.randomUUID().toString();
		UserObject user = findByUsername(username);
		if(user!=null && user.getPassword().equals(password)) {
			user.setTokenAndUpdate(uuid, byToken);
			return uuid;
		}
		return null;
	}

	@Override
	public void logout(String username, String token) {
		UserObject user = findByUsername(username);
		if(user!=null && user.getToken()!=null && user.getToken().equals(token)) {
			user.setTokenAndUpdate(null, byToken);
		}else {
			throw new IllegalArgumentException("No user or token exists.");
		}
	}

}
