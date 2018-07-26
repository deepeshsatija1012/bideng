package bidengine.app.services.user.auth;

import bidengine.app.entity.UserObject;

public interface UserAuthenticationService {

	String login(String username, String password);

	UserObject findByToken(String token);

	void logout(String userame, String token);
}
