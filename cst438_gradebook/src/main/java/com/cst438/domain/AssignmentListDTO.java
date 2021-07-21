package com.cst438.domain;

import java.util.ArrayList;

/*
 * a transfer object that is a list of assignment details
 */
public class AssignmentListDTO {

	public static class AssignmentDTO {
		public int assignmentId;
		public String assignmentName;
		public String dueDate;
		public String courseTitle;
		public int courseId;

		public AssignmentDTO(int assignmentId, int courseId, String assignmentName, String dueDate,
				String courseTitle) {
			this.assignmentId = assignmentId;
			this.courseId = courseId;
			this.assignmentName = assignmentName;
			this.dueDate = dueDate;
			this.courseTitle = courseTitle;
		}
	}

	public ArrayList<AssignmentDTO> assignments = new ArrayList<>();

}
