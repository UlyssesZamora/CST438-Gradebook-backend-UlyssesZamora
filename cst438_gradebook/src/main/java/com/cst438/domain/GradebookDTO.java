package com.cst438.domain;

import java.util.ArrayList;
import java.util.List;

public class GradebookDTO {
	
	public static class Grade{
		public int assignmentGradeId; 	//  primary key
		public String name; 			// student name
		public String email; 			// student email
		public String  grade;  			// assignment score 
	}
	
	public String assignmentName;
	public int assignmentId;
	public List<Grade> grades = new ArrayList<>();
	
}
