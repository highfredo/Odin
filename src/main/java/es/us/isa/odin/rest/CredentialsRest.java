package es.us.isa.odin.rest;

import es.us.isa.odin.rest.base.RestResource;


public class CredentialsRest extends RestResource {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}