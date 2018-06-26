package fr.formation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().antMatchers("/static/**").permitAll()
		.anyRequest().authenticated().and().formLogin()
		.loginPage("/securitycontroller/login")
		.loginProcessingUrl("/login")
		.defaultSuccessUrl("/usercontroller/Start", true)					// --> on renseigne la méthode à envoyer lorsque le login est correct
		.failureUrl("/securitycontroller/login?error=true").permitAll()		
		.and().logout().invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/securitycontroller/login?logout=true")
		.permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
	    throws Exception {
	auth.inMemoryAuthentication().withUser("user").password("{noop}123")
		.roles("USER");
	auth.inMemoryAuthentication().withUser("admin").password("{noop}123")
		.roles("ADMIN");									
	auth.inMemoryAuthentication().withUser("guest").password("{noop}123")
		.roles("GUEST");
	auth.inMemoryAuthentication().withUser("root").password("{noop}123")
	.roles("ADMIN","USER","GUEST");											// on peut définir plusieurs roles si on veut
    }
}
