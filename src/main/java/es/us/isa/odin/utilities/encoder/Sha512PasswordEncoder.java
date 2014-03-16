package es.us.isa.odin.utilities.encoder;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class Sha512PasswordEncoder extends ShaPasswordEncoder {

	public Sha512PasswordEncoder() {
		super(512);
	}
	
}
