package com.cst438;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cst438.services.RegistrationService;
import com.cst438.services.RegistrationServiceMQ;
import com.cst438.services.RegistrationServiceREST;

@SpringBootApplication
public class Cst438GradebookApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Cst438GradebookApplication.class, args);
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
	
	@Override
   	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors();
 		http.authorizeRequests().antMatchers("/**").permitAll();
	}
	
	// this is necessary to allow cross-origin http requests from React front end. 
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://accounts.google.com"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);
		config.applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
}
