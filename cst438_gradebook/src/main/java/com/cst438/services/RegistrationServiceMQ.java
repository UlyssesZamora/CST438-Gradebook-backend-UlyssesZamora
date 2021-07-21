package com.cst438.services;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.cst438.domain.Course;
import com.cst438.domain.CourseDTOG;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentDTO;
import com.cst438.domain.EnrollmentRepository;


public class RegistrationServiceMQ extends RegistrationService {

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public RegistrationServiceMQ() {
		System.out.println("MQ registration service ");
	}

	// ----- configuration of message queues
	String registrationExchange = "registration-exchange";


	// ----- end of configuration of message queue

	// receiver of messages from Registration service
	
	@RabbitListener(queues = "gradebook-queue")
	public void receive(EnrollmentDTO enrollmentDTO) {
		System.out.println("Receive enrollment :" + enrollmentDTO);
		Course c = courseRepository.findByCourse_id(enrollmentDTO.course_id);
		if (c != null) {
			Enrollment e = new Enrollment();
			e.setCourse(c);
			e.setStudentEmail(enrollmentDTO.studentEmail);
			e.setStudentName(enrollmentDTO.studentName);
			enrollmentRepository.save(e);
			System.out.println("Success");
		} else {
			System.out.println("Fail");
		}
	}

	// sender of messages to Registration Service
	@Override
	public void sendFinalGrades(int course_id, CourseDTOG courseDTO) {
		 
		System.out.println("Sending final grades rabbitmq: " + course_id);
		
		// TODO an error such as exchange not found, will not be reported 
		//      back to caller here because the action is asynchronous. 
		//      Either make the call transactional, in which case it will 
		//      wait for commit or monitor the log for ERROR message
		//  channel error; ... (reply-code=404, reply-text=NOT_FOUND - no exchange 'registration-exchange' ...)
		
		rabbitTemplate.convertAndSend(registrationExchange, "", // routing key none.
				courseDTO);
		
	}

}
