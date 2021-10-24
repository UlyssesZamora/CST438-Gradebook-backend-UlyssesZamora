package com.cst438.domain;

import java.util.ArrayList;
import java.util.List;

public class GradebookDTO {
	
	public static class Grade{
		public int assignmentGradeId; 	//  primary key
		public String name; 			// student name
		public String email; 			// student email
		public String  grade;  			// assignment score
		
		@Override
		public String toString() {
			return "Grade [assignmentGradeId=" + assignmentGradeId + ", name=" + name + ", email=" + email + ", grade="
					+ grade + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Grade other = (Grade) obj;
			if (assignmentGradeId != other.assignmentGradeId)
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (grade == null) {
				if (other.grade != null)
					return false;
			} else if (!grade.equals(other.grade))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
		
	}
	
	public String assignmentName;
	public int assignmentId;
	public List<Grade> grades = new ArrayList<>();
	
	@Override
	public String toString() {
		return "GradebookDTO [assignmentName=" + assignmentName + ", assignmentId=" + assignmentId + ", grades="
				+ grades + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GradebookDTO other = (GradebookDTO) obj;
		if (assignmentId != other.assignmentId)
			return false;
		if (assignmentName == null) {
			if (other.assignmentName != null)
				return false;
		} else if (!assignmentName.equals(other.assignmentName))
			return false;
		if (grades == null) {
			if (other.grades != null)
				return false;
		} else if (!grades.equals(other.grades))
			return false;
		return true;
	}
	
	
	
}
