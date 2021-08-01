package com.cst438;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.cst438.services.RegistrationService;
import com.cst438.services.RegistrationServiceMQ;
import com.cst438.services.RegistrationServiceREST;

@SpringBootApplication
public class Cst438GradebookApplication  {

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

}
