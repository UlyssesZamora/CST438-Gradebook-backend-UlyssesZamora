package com.cst438.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AssignmentGrade {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="enrollment_id")
	private Enrollment studentEnrollment;
	
	@ManyToOne
	@JoinColumn(name="assignment_id")
	private Assignment assignment;
	
	private String score;
	
	public AssignmentGrade() { }
	
	public AssignmentGrade(Assignment assignment, Enrollment enrollment) {
		this.assignment = assignment;
		this.studentEnrollment = enrollment;
		this.score = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Enrollment getStudentEnrollment() {
		return studentEnrollment;
	}

	public void setStudentEnrollment(Enrollment studentEnrollment) {
		this.studentEnrollment = studentEnrollment;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "AssignmentGrades [id=" + id + ", studentEnrollment=" + studentEnrollment + ", assignment=" + assignment
				+ ", score=" + score + "]";
	}

    // equality is based on id and score attributes only!
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssignmentGrade other = (AssignmentGrade) obj;
		if (id != other.id)
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		return true;
	}
	
	

}
