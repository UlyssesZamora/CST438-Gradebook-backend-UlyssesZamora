package com.cst438;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentGrade;
import com.cst438.domain.AssignmentGradeRepository;
import com.cst438.domain.AssignmentRepository;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;

@SpringBootTest
public class EndToEndTestCreateAssignment {
	
	public static final String CHROME_DRIVER_FILE_LOCATION = "/Users/ulysseszamora/Downloads/chromedriver";

	public static final String URL = "http://localhost:3000/assignment/new";
	public static final int SLEEP_DURATION = 1000; // 1 second.
	public static final String TEST_ASSIGNMENT_NAME = "End to End Test Assignment";
	public static final int TEST_COURSE_ID = 99999;
	public static final String TEST_ASSIGNMENT_DUEDATE = "2001-10-11";
	public static final String TEST_INSTRUCTOR_EMAIL = "dwisneski@csumb.edu";
	public static final String TEST_COURSE_TITLE = "Test Course";
	public static final String TOAST_SUCCESS_MESSAGE = "Assignment Successfully Added";

	
	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	AssignmentGradeRepository assignnmentGradeRepository;

	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Test
	public void createAssignmentTest() throws Exception {
		List<Assignment> a = new ArrayList<Assignment>();
		Course c = new Course();
		c.setCourse_id(TEST_COURSE_ID);
		c.setInstructor(TEST_INSTRUCTOR_EMAIL);
		c.setSemester("Fall");
		c.setYear(2021);
		c.setTitle(TEST_COURSE_TITLE);
		courseRepository.save(c);
		
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(URL);
		Thread.sleep(SLEEP_DURATION);
		
		try {
			driver.findElement(By.id("assignmentName")).sendKeys(TEST_ASSIGNMENT_NAME);;
			
			driver.findElement(By.id("assignmentDueDate")).sendKeys(TEST_ASSIGNMENT_DUEDATE);;
			
			driver.findElement(By.id("courseId")).sendKeys(String.valueOf(TEST_COURSE_ID));
			
			driver.findElement(By.id("Submit")).click();
			Thread.sleep(SLEEP_DURATION * 2);
			
			String toastTitle = driver.findElement(By.cssSelector(".Toastify__toast-body")).getText();
			
			for(Assignment assign : assignmentRepository.findAll()) {
				if(assign.getCourse().getCourse_id() == TEST_COURSE_ID) {
					a.add(assign);
				}
			}
			
			// Checking to make sure one assignment got added
			assertEquals(a.size(), 1);
			
			// Checking to make sure the result message was a success
			assertEquals(toastTitle, TOAST_SUCCESS_MESSAGE);
			
			// Checking to make sure the assignment course matches the test course
			assertEquals(a.get(0).getCourse(), c);
			

		} catch (Exception ex) {
			throw ex;
		} finally {
			for(Assignment as : a) {
				assignmentRepository.delete(as);
			}
		
			// Checking to make sure all assignments are deleted
			for(Assignment as : assignmentRepository.findAll()) {
				assertNotEquals(as.getCourse(), c);
			}
			
			courseRepository.delete(c);
			
			// CHecking to make sure course is removed from database
			assertEquals(courseRepository.findById(TEST_COURSE_ID), Optional.empty());
			driver.quit();
		}
		
	}

}
