package es.us.isa.odin.conf.initializers;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import es.us.isa.odin.conf.SecurityConfig;
import es.us.isa.odin.conf.WebConfig;

/**
 * http://static.springsource.org/spring-framework/docs/3.2.0.RELEASE/spring-framework-reference/html/mvc.html#mvc-
 * container-config
 * 
 * @author Geoffroy Warin (https://github.com/geowarin)
 * 
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class, SecurityConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	/*
	 * @Override protected Filter[] getServletFilters() {
	 * 
	 * CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	 * characterEncodingFilter.setEncoding("UTF-8"); return new Filter[] { characterEncodingFilter, new SiteMeshFilter()
	 * }; }
	 */
}