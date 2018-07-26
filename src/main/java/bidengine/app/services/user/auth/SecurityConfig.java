//package bidengine.app.services.user.auth;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
//import org.springframework.security.web.util.matcher.RegexRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	private static final RequestMatcher PUBLIC_URLS_USER = new RegexRequestMatcher(
//			"/bidengine/webapi/bidservices/(user|userlogin)", HttpMethod.POST.name());
//	private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS_USER);
//
//	private TokenAuthServiceProvider provider;
//
//	public SecurityConfig(final TokenAuthServiceProvider provider) {
//		this.provider = provider;
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(provider);
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().requestMatchers(PUBLIC_URLS_USER);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().exceptionHandling()
//		.defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), PROTECTED_URLS)
//		.and()
//		.authenticationProvider(provider)
//		.authorizeRequests().anyRequest().authenticated().
//		and()
//		.csrf().disable()
//		.formLogin().disable()
//		.httpBasic().disable()
//		.logout().disable();
//	}
//
//	@Bean
//	SimpleUrlAuthenticationSuccessHandler successHandler() {
//		final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
//		successHandler.setRedirectStrategy(new RedirectStrategy() {
//
//			@Override
//			public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url)
//					throws IOException {
//			}
//		});
//		return successHandler;
//	}
//
//	@Bean
//	TokenAuthenticationFilter restAuthenticationFilter() throws Exception {
//		final TokenAuthenticationFilter filter = new TokenAuthenticationFilter(PROTECTED_URLS);
//		filter.setAuthenticationManager(authenticationManager());
//		filter.setAuthenticationSuccessHandler(successHandler());
//		return filter;
//	}
//
//	@Bean
//	AuthenticationEntryPoint forbiddenEntryPoint() {
//		return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
//	}
//}
