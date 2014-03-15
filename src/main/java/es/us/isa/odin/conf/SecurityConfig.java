package es.us.isa.odin.conf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import es.us.isa.odin.security.UserAccountService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        		//.antMatchers("/", "/home").permitAll()
        		.anyRequest().permitAll(); //authenticated();
        
        http
        .formLogin()
            .defaultSuccessUrl("/hello")
            //.loginPage("/security/login")
            //.permitAll()
            .and()
        .logout()
            .permitAll();
        
        
        http.requiresChannel().anyRequest().requiresSecure();
        http.csrf().disable();
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(new UserAccountService());
    	/*
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
        */
    }
}