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

@Service
public class UserAccountService implements UserDetailsService {
	
	private static final String salt = "eustaquio33$";

	@Autowired
	UserAccountRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).getEntity();
	}
	
	public Document<UserAccount> signUp(Credentials credentials) {
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(credentials.getUsername());
		userAccount.setPassword(credentials.getPassword());
		userAccount.addAuthority(new Authority("USER"));
		// TODO: añadir salt -> userAccount.setPassword(Sha2Crypt.sha256Crypt(userAccount.getPassword().getBytes(), salt));
		return userRepository.save(new Document<UserAccount>(userAccount));
	}
	
	public static UserAccount getPrincipal() {
		UserAccount result;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Object principal = authentication.getPrincipal();
		result = (UserAccount) principal;

		return result;
	}

}
