package com.project.student.classes.model;

public class ClassesModel {
	private String codeClass;
	private String departmentClass;
	private String instructorClass;
	
	public void setCodeClass(String code) {
		this.codeClass = code;
	}
	public String getCodeClass() {
		return codeClass;
	}
	
	public void setDepartmentClass(String department) {
		this.departmentClass = department;
	}
	public String getDepartmentClass() {
		return departmentClass;
	}
	
	public void setInstructorClass(String instructor) {
		this.instructorClass = instructor;
	}
	public String getInstructorClass() {
		return instructorClass;
	}
}
