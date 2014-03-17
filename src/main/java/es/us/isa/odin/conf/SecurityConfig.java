package es.us.isa.odin.conf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import es.us.isa.odin.security.UserAccountService;
import es.us.isa.odin.utilities.encoder.Sha512PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"es.us.isa.odin"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAccountService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/security/**").permitAll()
        	.antMatchers("/**").permitAll();//.hasRole("ADMIN");
        		
        http.formLogin()
            .defaultSuccessUrl("/")
            .loginPage("/security/login")
            .loginProcessingUrl("/security/login")
            .permitAll();
        
        http.logout()
        	.logoutUrl("/security/logout")
        	.logoutSuccessUrl("/")
        	.invalidateHttpSession(true);
        
        http.csrf().disable();
	}
	
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(new Sha512PasswordEncoder());
    }
}