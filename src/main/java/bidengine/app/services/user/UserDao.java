package bidengine.app.services.user;

import bidengine.app.entity.UserObject;

public interface UserDao {
	
	UserObject findByUsername(String username);
	UserObject findByUserid(String userid);
	
	UserObject tryAndCreateNewUser(String username, String password);
}
