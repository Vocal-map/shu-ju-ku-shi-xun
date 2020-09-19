package com.project.student.result.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.student.result.model.ResultModel;

public class ResultDao {
	public int insert(ResultModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "insert into result(student_result, course_result, result_result) values(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getStudentResult());
		ps.setString(2, model.getCourseResult());
		ps.setString(3, model.getResultResult());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int delete(ResultModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "delete from result where student_result = ? and course_result = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getStudentResult());
		ps.setString(2, model.getCourseResult());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int update(ResultModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "update result set result_result=? where student_result = ? and course_result = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getResultResult());
		ps.setString(2, model.getStudentResult());
		ps.setString(3, model.getCourseResult());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public ResultModel selectByCode(ResultModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from result where student_result = ? and course_result = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, model.getStudentResult());
		ps.setString(2, model.getCourseResult());
        ResultSet rs = ps.executeQuery();
        ResultModel reModel = null;
        while(rs.next()) {
        	String studentResult = rs.getString("student_result");
        	String courseResult = rs.getString("course_result");
        	String resultResult = rs.getString("result_result");
        	reModel = new ResultModel();
        	reModel.setStudentResult(studentResult);
        	reModel.setCourseResult(courseResult);
        	reModel.setResultResult(resultResult);
        }
        rs.close();
        ps.close();
        conn.close();
        return reModel;
	}
	public List<ResultModel> selectList(ResultModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select student_result, course_result, result_result, "
				+ "(select name_course from course where course.code_course=result.course_result) courseNameResult "
				+ "from result where student_result like ? and course_result like ? order by course_result asc, result_result asc";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%"+model.getStudentResult()+"%");
		ps.setString(2, "%"+model.getCourseResult()+"%");
        ResultSet rs = ps.executeQuery();
        List<ResultModel> list = new ArrayList<>();
        while (rs.next()) {
        	String studentResult = rs.getString("student_result");
        	String courseResult = rs.getString("course_result");
        	String resultResult = rs.getString("result_result");
        	String courseNameResult = rs.getString("courseNameResult");
        	ResultModel reModel = new ResultModel();
        	reModel.setStudentResult(studentResult);
        	reModel.setCourseResult(courseResult);
        	reModel.setResultResult(resultResult);
        	reModel.setCourseNameResult(courseNameResult);
            list.add(reModel);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}
}
