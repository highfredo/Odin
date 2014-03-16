package es.us.isa.odin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.us.isa.odin.domain.Document;
import es.us.isa.odin.repositories.UserAccountRepository;
import es.us.isa.odin.rest.CredentialsRest;
import es.us.isa.odin.utilities.encoder.Sha512PasswordEncoder;

@Service
public class UserAccountService implements UserDetailsService {
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	private Sha512PasswordEncoder passwordEncoder = new Sha512PasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userAccountRepository.findByUsername(username).getEntity();
	}
	
	public Document<UserAccount> signUp(CredentialsRest credentials) {
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(credentials.getUsername());
		userAccount.setPassword(passwordEncoder.encodePassword(credentials.getPassword(), null));
		userAccount.addAuthority(Authority.USER);
		
		return userAccountRepository.save(new Document<UserAccount>(userAccount));
	}
	
	
	public static UserAccount getPrincipal() {
		UserAccount result;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Object principal = authentication.getPrincipal();
		result = (UserAccount) principal;

		return result;
	}

}
