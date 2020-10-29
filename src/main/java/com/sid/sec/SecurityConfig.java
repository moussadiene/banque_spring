package com.sid.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



// pour configrer
@Configuration
//pour activer la securité web
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	// creer des utlisateurs
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("djine").password("passer123").roles("ADMIN","USER");
//		auth.inMemoryAuthentication().withUser("user").password("passer").roles("USER");
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal, password as credentials, etat from users where username=?")
		.authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
		.rolePrefix("ROLE_");
		
	}
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
	// regle et strategie de securite
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
//		http.authorizeRequests().antMatchers("/operation","/consulterCompte").hasRole("USER");
//		http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN");
//		
//		http.exceptionHandling().accessDeniedPage("/403");
		super.configure(http);
	}
}
