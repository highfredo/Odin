package es.us.isa.odin.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8625195957703103725L;
	
	public static final Authority USER = new Authority("USER");
	public static final Authority ADMIN = new Authority("ADMIN");
	
	private String authority;
	
	public Authority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
