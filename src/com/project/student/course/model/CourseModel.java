package com.project.student.course.model;

public class CourseModel {
	private String codeCourse;
	private String nameCourse;
	private String creditCourse;
	private String periodCourse;
	
	public void setCodeCourse(String code) {
		this.codeCourse = code;
	}
	public String getCodeCourse() {
		return codeCourse;
	}
	
	public void setNameCourse(String name) {
		this.nameCourse = name;
	}
	public String getNameCourse() {
		return nameCourse;
	}
	
	public void setCreditCourse(String credit) {
		this.creditCourse = credit;
	}
	public String getCreditCourse() {
		return creditCourse;
	}
	
	public void setPeriodCourse(String period) {
		this.periodCourse = period;
	}
	public String getPeriodCourse() {
		return periodCourse;
	}
}
