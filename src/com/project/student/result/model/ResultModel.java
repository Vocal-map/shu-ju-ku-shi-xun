package com.project.student.result.model;

public class ResultModel {
	private String studentResult;
	private String courseResult;
	private String resultResult;
	private String courseNameResult;
	public void setStudentResult(String student) {
		this.studentResult = student;
	}
	public String getCourseNameResult() {
		return courseNameResult;
	}
	public void setCourseNameResult(String courseNameResult) {
		this.courseNameResult = courseNameResult;
	}
	public String getStudentResult() {
		return studentResult;
	}
	
	public void setCourseResult(String course) {
		this.courseResult = course;
	}
	public String getCourseResult() {
		return courseResult;
	}
	
	public void setResultResult(String result) {
		this.resultResult = result;
	}
	public String getResultResult() {
		return resultResult;
	}
}
