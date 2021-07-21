package com.cst438.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String studentName;
	private String studentEmail;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToMany(mappedBy="studentEnrollment")
	List<AssignmentGrade> assignmentGrades;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	

	public List<AssignmentGrade> getAssignmentGrades() {
		return assignmentGrades;
	}

	public void setAssignmentGrades(List<AssignmentGrade> assignmentGrades) {
		this.assignmentGrades = assignmentGrades;
	}

	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", studentName=" + studentName + ", studentEmail=" + studentEmail + ", course="
				+ course + "]";
	}

	
	
}
