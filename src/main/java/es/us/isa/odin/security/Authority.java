package es.us.isa.odin.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8625195957703103725L;
	
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
