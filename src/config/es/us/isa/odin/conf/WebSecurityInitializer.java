package es.us.isa.odin.conf;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class WebSecurityInitializer  extends AbstractSecurityWebApplicationInitializer {
	
	public WebSecurityInitializer() {
        super(SecurityConfig.class);
    }
}