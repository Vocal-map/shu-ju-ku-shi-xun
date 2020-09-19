package com.project.student.classes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.student.classes.model.ClassesModel;

public class ClassesDao {
	public int insert(ClassesModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "insert into classes(code_class, department_class, instructor_class) values(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getCodeClass());
		ps.setString(2, model.getDepartmentClass());
		ps.setString(3, model.getInstructorClass());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int delete(ClassesModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "delete from classes where code_class = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getCodeClass());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int update(ClassesModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "update classes set department_class=?, instructor_class=?  where code_class=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getDepartmentClass());
		ps.setString(2, model.getInstructorClass());
		ps.setString(3, model.getCodeClass());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public ClassesModel selectByCode(ClassesModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from classes where code_class=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, model.getCodeClass());
        ResultSet rs = ps.executeQuery();
        ClassesModel clModel = null;
        while(rs.next()) {
        	String code = rs.getString("code_class");
            String department = rs.getString("department_class");
            String instructor = rs.getString("instructor_class");
        	clModel = new ClassesModel();
        	clModel.setCodeClass(code);
        	clModel.setDepartmentClass(department);
        	clModel.setInstructorClass(instructor);
        }
        rs.close();
        ps.close();
        conn.close();
        return clModel;
	}
	public ClassesModel selectByInstructor(ClassesModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from classes where instructor_class=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, model.getInstructorClass());
        ResultSet rs = ps.executeQuery();
        ClassesModel clModel = null;
        if(rs.next()) {
        	String code = rs.getString("code_class");
            String department = rs.getString("department_class");
            String instructor = rs.getString("instructor_class");
        	clModel = new ClassesModel();
        	clModel.setCodeClass(code);
        	clModel.setDepartmentClass(department);
        	clModel.setInstructorClass(instructor);
        }
        rs.close();
        ps.close();
        conn.close();
        return clModel;
	}
	public List<ClassesModel> selectList(ClassesModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from classes where code_class like ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%"+model.getCodeClass()+"%");
        ResultSet rs = ps.executeQuery();
        List<ClassesModel> list = new ArrayList<>();
        while (rs.next()) {
        	String code = rs.getString("code_class");
            String department = rs.getString("department_class");
            String instructor = rs.getString("instructor_class");
        	ClassesModel clModel = new ClassesModel();
        	clModel.setCodeClass(code);
        	clModel.setDepartmentClass(department);
        	clModel.setInstructorClass(instructor);
            list.add(clModel);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ClassesDao cDao = new ClassesDao();
		ClassesModel cModel = new ClassesModel();
		cModel.setCodeClass("计科1803");
		ClassesModel re = cDao.selectByCode(cModel);
		System.out.printf("%s,%s,%s", re.getCodeClass(),re.getDepartmentClass(),re.getInstructorClass());
	}
}
