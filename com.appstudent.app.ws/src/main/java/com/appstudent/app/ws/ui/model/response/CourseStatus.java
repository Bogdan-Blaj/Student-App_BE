package com.appstudent.app.ws.ui.model.response;

public enum CourseStatus {
	COURSE_FAILED("Course failed, need to take the exam again"),
	COURSE_PASSED("Course passed, congratuilation"),
	COURSE_PENDING_EXAM("Exam will be soon.");
	
	private String courseDetails;

	public String getCourseDetails() {
		return courseDetails;
	}

	public void setCourseDetails(String courseDetails) {
		this.courseDetails = courseDetails;
	}

	private CourseStatus(String courseDetails) {
		this.courseDetails = courseDetails;
	}
	
	
}
