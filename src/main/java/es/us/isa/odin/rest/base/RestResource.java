package es.us.isa.odin.rest.base;

import org.springframework.hateoas.ResourceSupport;


public class RestResource extends ResourceSupport {

	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
