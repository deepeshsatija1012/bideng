//package bidengine;
//
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//import bidengine.app.services.user.auth.SecurityConfig;
//
//public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return new Class[] {AppConfig.class, SecurityConfig.class};
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return null;
//	}
//
//	@Override
//	protected String[] getServletMappings() {
//		return new String[] {"/bidengine/*"};
//	}
//
//}
