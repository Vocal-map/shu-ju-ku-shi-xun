package com.project.student.course.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.student.course.model.CourseModel;



public class CourseDao {
	public int insert(CourseModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "insert into course(code_course, name_course, credit_course, period_course) values(?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getCodeCourse());
		ps.setString(2, model.getNameCourse());
		ps.setString(3, model.getCreditCourse());
		ps.setString(4, model.getPeriodCourse());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int delete(CourseModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "delete from course where code_course = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getCodeCourse());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int update(CourseModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "update course set name_course=?, credit_course=?, period_course=?  where code_course = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getNameCourse());
		System.out.println(model.getCodeCourse());
		System.out.println(model.getNameCourse());
		ps.setString(2, model.getCreditCourse());
		ps.setString(3, model.getPeriodCourse());
		ps.setString(4, model.getCodeCourse());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public CourseModel selectByCode(CourseModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from course where code_course=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, model.getCodeCourse());
        ResultSet rs = ps.executeQuery();
        CourseModel coModel = null;
        while(rs.next()) {
        	String codeCourse = rs.getString("code_course");
        	String nameCourse = rs.getString("name_course");
        	String creditCourse = rs.getString("credit_course");
        	String periodCourse = rs.getString("period_course");
        	coModel = new CourseModel();
        	coModel.setCodeCourse(codeCourse);
        	coModel.setCreditCourse(creditCourse);
        	coModel.setNameCourse(nameCourse);
        	coModel.setPeriodCourse(periodCourse);
        }
        rs.close();
        ps.close();
        conn.close();
        return coModel;
	}
	public List<CourseModel> selectList(CourseModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from course where name_course like ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%"+model.getNameCourse()+"%");
        ResultSet rs = ps.executeQuery();
        List<CourseModel> list = new ArrayList<>();
        while (rs.next()) {
        	String codeCourse = rs.getString("code_course");
        	String nameCourse = rs.getString("name_course");
        	String creditCourse = rs.getString("credit_course");
        	String periodCourse = rs.getString("period_course");
        	CourseModel coModel = new CourseModel();
        	coModel.setCodeCourse(codeCourse);
        	coModel.setCreditCourse(creditCourse);
        	coModel.setNameCourse(nameCourse);
        	coModel.setPeriodCourse(periodCourse);
            list.add(coModel);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}
}
