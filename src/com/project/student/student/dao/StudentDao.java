package com.project.student.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.student.student.model.StudentModel;

public class StudentDao {
	public int insert(StudentModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "insert into student(code_student, name_student, sex_student, tel_student, "
				+ "academy_student, professional_student, grade_student, class_student, dormitory_student) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getCodeStudent());
		ps.setString(2, model.getNameStudent());
		ps.setString(3, model.getSexStudent());
		ps.setString(4, model.getTelStudent());
		ps.setString(5, model.getAcademyStudent());
		ps.setString(6, model.getProfessionalStudent());
		ps.setString(7, model.getGradeStudent());
		ps.setString(8, model.getClassStudent());
		ps.setString(9, model.getDormitoryStudent());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int delete(StudentModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "delete from student where code_student=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getCodeStudent());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int update(StudentModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "update student set name_student=?, sex_student=?, tel_student=?,"
				+ "academy_student=?, professional_student=?, grade_student=?, class_student=?,"
				+ "dormitory_student=? where code_student=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getNameStudent());
		ps.setString(2, model.getSexStudent());
		ps.setString(3, model.getTelStudent());
		ps.setString(4, model.getAcademyStudent());
		ps.setString(5, model.getProfessionalStudent());
		ps.setString(6, model.getGradeStudent());
		ps.setString(7, model.getClassStudent());
		ps.setString(8, model.getDormitoryStudent());
		ps.setString(9, model.getCodeStudent());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public StudentModel selectByCode(StudentModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from student where code_student=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, model.getCodeStudent());
        ResultSet rs = ps.executeQuery();
        StudentModel reModel = null;
        while(rs.next()) {
        	String codeStudent = rs.getString("code_student");
        	String nameStudent = rs.getString("name_student");
        	String sexStudent = rs.getString("sex_student");
        	String telStudent = rs.getString("tel_student");
        	String academyStudent = rs.getString("academy_student");
        	String professionalStudent = rs.getString("professional_student");
        	String gradeStudent = rs.getString("grade_student");
        	String classStudent = rs.getString("class_student");
        	String dormitoryStudent = rs.getString("dormitory_student");
        	reModel = new StudentModel();
        	reModel.setCodeStudent(codeStudent);
        	reModel.setNameStudent(nameStudent);
        	reModel.setSexStudent(sexStudent);
        	reModel.setTelStudent(telStudent);
        	reModel.setAcademyStudent(academyStudent);
        	reModel.setProfessionalStudent(professionalStudent);
        	reModel.setGradeStudent(gradeStudent);
        	reModel.setClassStudent(classStudent);
        	reModel.setDormitoryStudent(dormitoryStudent);
        }
        rs.close();
        ps.close();
        conn.close();
        return reModel;
	}
	public List<StudentModel> selectList(StudentModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from student where code_student like ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%"+model.getCodeStudent()+"%");
        ResultSet rs = ps.executeQuery();
        List<StudentModel> list = new ArrayList<>();
        while (rs.next()) {
        	String codeStudent = rs.getString("code_student");
        	String nameStudent = rs.getString("name_student");
        	String sexStudent = rs.getString("sex_student");
        	String telStudent = rs.getString("tel_student");
        	String academyStudent = rs.getString("academy_student");
        	String professionalStudent = rs.getString("professional_student");
        	String gradeStudent = rs.getString("grade_student");
        	String classStudent = rs.getString("class_student");
        	String dormitoryStudent = rs.getString("dormitory_student");
        	StudentModel reModel = new StudentModel();
        	reModel.setCodeStudent(codeStudent);
        	reModel.setNameStudent(nameStudent);
        	reModel.setSexStudent(sexStudent);
        	reModel.setTelStudent(telStudent);
        	reModel.setAcademyStudent(academyStudent);
        	reModel.setProfessionalStudent(professionalStudent);
        	reModel.setGradeStudent(gradeStudent);
        	reModel.setClassStudent(classStudent);
        	reModel.setDormitoryStudent(dormitoryStudent);
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
