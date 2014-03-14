package es.us.isa.odin.conf.initializers;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import es.us.isa.odin.conf.SecurityConfig;

public class SecurityInitializer  extends AbstractSecurityWebApplicationInitializer {
	
	public SecurityInitializer() {
        super(SecurityConfig.class);
    }
}