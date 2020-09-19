package com.project.student.count.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.student.count.model.CountModel;

public class CountDao {
	public List<CountModel> selectList() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select class_student, code_student, name_student, sex_student,\r\n" + 
				"(select count(sex_student) from student s2 where s1.class_student=s2.class_student) count,\r\n" + 
				"(select count(sex_student) from student s2 where s1.class_student=s2.class_student and sex_student='ÄÐ') countMan,\r\n" + 
				"(select count(sex_student) from student s2 where s1.class_student=s2.class_student and sex_student='Å®') countWoman\r\n" + 
				"from student s1 order by class_student";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<CountModel> list = new ArrayList<>();
        while (rs.next()) {
        	String classStudent = rs.getString("class_student");
        	String codeStudent = rs.getString("code_student");
        	String nameStudent = rs.getString("name_student");
        	String sexStudent = rs.getString("sex_student");
        	String count = rs.getString("count");
        	String countMan = rs.getString("countMan");
        	String countWoman = rs.getString("countWoman");
        	CountModel coModel = new CountModel();
        	coModel.setClassStudent(classStudent);
        	coModel.setCodeStudent(codeStudent);
        	coModel.setNameStudent(nameStudent);
        	coModel.setSexStudent(sexStudent);
        	coModel.setCountMan(countMan);
        	coModel.setCountPeople(count);
        	coModel.setCountWoman(countWoman);
            list.add(coModel);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
	}
	public List<CountModel> selectListDor() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select dormitory_student,  code_student, name_student,\r\n" + 
				"(select count(code_student) from student s2 where s1.dormitory_student=s2.dormitory_student ) countDor\r\n" + 
				"from student s1 order by dormitory_student";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<CountModel> list = new ArrayList<>();
        while (rs.next()) {
        	String dorStudent = rs.getString("dormitory_student");
        	String codeStudent = rs.getString("code_student");
        	String nameStudent = rs.getString("name_student");
        	String countDor = rs.getString("countDor");
        	CountModel coModel = new CountModel();
        	coModel.setDorStudent(dorStudent);
        	coModel.setCodeStudent(codeStudent);
        	coModel.setNameStudent(nameStudent);
        	coModel.setCountDor(countDor);
            list.add(coModel);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
	}
	public List<CountModel> selectListRe() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select course_result, (select name_course from course where course.code_course=result.course_result) courseNameResult,\r\n" + 
				"(select name_student from student where student.code_student=result.student_result) studentNameResult, student_result,\r\n" + 
				"result_result, (select count(student_result) from result r2 where result.result_result=r2.result_result) countRe\r\n" + 
				"from result  order by course_result asc, result_result asc";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<CountModel> list = new ArrayList<>();
        while (rs.next()) {
        	String courseResult = rs.getString("course_result");
        	String courseNameResult = rs.getString("courseNameResult");
        	String nameStudent = rs.getString("studentNameResult");
        	String codeStudent = rs.getString("student_result");
        	String resultResult = rs.getString("result_result");
        	String countRe = rs.getString("countRe");
        	CountModel coModel = new CountModel();
        	coModel.setCourseResult(courseResult);
        	coModel.setCourseNameResult(courseNameResult);
        	coModel.setNameStudent(nameStudent);
        	coModel.setCodeStudent(codeStudent);
        	coModel.setResultResult(resultResult);
        	coModel.setCountRe(countRe);
            list.add(coModel);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
	}
}
