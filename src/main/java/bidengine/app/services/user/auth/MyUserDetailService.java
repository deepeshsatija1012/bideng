package bidengine.app.services.user.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import bidengine.app.entity.UserObject;
import bidengine.app.entity.UserStatus;
import bidengine.app.services.user.UserDao;

@Component("userDetailService")
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	private static final GrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");
	private static final GrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");
	private static final List<GrantedAuthority> GRANTED = new ArrayList<>();
	static {
		GRANTED.add(ROLE_ADMIN);GRANTED.add(ROLE_USER);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserObject user = userDao.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("No user found with name "+ username);
		}
		return User.builder().username(user.getUsername())
		.password(user.getPassword()).authorities(GRANTED)
		.accountLocked(user.getUserStatus()==UserStatus.INACTIVE)
		.disabled(user.getUserStatus()==UserStatus.INACTIVE)
		.roles("ROLE_USER","ROLE_ADMIN")
		.build();
	}

}
