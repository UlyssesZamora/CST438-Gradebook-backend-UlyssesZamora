package com.cst438.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Course {
	
	@Id
	private int course_id;
	private String title;
	private String instructor;
	private int year;
	private String semester;
	
	@OneToMany(mappedBy="course")
	@OrderBy("studentName ASC")
	List<Enrollment> enrollments;
	
	@OneToMany(mappedBy="course")
	List<Assignment> assignments;
	
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", title=" + title + ", instructor=" + instructor + ", year=" + year
				+ ", semester=" + semester + "]";
	}
	
}
