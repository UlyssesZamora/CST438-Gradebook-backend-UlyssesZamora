package com.cst438;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cst438.services.RegistrationService;
import com.cst438.services.RegistrationServiceMQ;
import com.cst438.services.RegistrationServiceREST;

@SpringBootApplication
@EnableWebSecurity
public class Cst438GradebookApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Cst438GradebookApplication.class, args);
	}
	
	@Override
   	protected void configure(HttpSecurity http) throws Exception {
 		
		// turn off security
		http.httpBasic();
 		http.csrf().disable().cors(); 		
 		
 		// permit requests to /enrollment without authentication. All other URLS are authenticated
 		http.authorizeRequests().mvcMatchers(HttpMethod.POST, "/enrollment").permitAll();
		 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3001", "https://accounts.google.com"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);
		config.applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	// basic security
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.withUsername("test@csumb.edu").password("{noop}test").authorities("read").build();
		UserDetails user2 = User.withUsername("dwisneski@csumb.edu").password("{noop}password").authorities("read").build();
		return new InMemoryUserDetailsManager(user1, user2);
		
	}
	
	@Bean(name = "RegistrationService")
	@ConditionalOnProperty(prefix = "registration", name = "service", havingValue = "MQ")
	public RegistrationService registrationServiceRESTMQ() {
		return new RegistrationServiceMQ();
	}
	
	
	@Bean(name = "RegistrationService")
	@ConditionalOnProperty(prefix = "registration", name = "service", havingValue = "REST")
	public RegistrationService registrationServiceREST() {
		return new RegistrationServiceREST();
	}
	
	@Bean(name = "RegistrationService")
	@ConditionalOnProperty(prefix = "registration", name = "service", havingValue = "default")
	public RegistrationService registrationServiceDefault() {
		return new RegistrationService();
	}
}
