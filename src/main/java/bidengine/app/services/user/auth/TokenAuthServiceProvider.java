package bidengine.app.services.user.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import bidengine.app.entity.UserObject;
import bidengine.app.entity.UserStatus;

@Component
public class TokenAuthServiceProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UserAuthenticationService userAuthService;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		Object creds = authentication.getCredentials();
		if(creds==null) {
			new AuthenticationCredentialsNotFoundException("No token found with header.");
		}
		String token = creds.toString();
		UserObject user = userAuthService.findByToken(token);
		if(user!=null && user.getUsername()!=null && user.getUsername().equals(username)) {
			UserDetails userDetails = User.builder().username(user.getUsername()).password(user.getPassword())
			.accountExpired(user.getUserStatus()==UserStatus.INACTIVE).accountLocked(false)
			.disabled(user.getUserStatus()==UserStatus.INACTIVE).credentialsExpired(false)
			.authorities(new ArrayList<>()).build();
			return userDetails;
		}
		throw new UsernameNotFoundException("User not found with token "+ token);
	}

}
