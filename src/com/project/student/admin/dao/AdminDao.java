package com.project.student.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.student.admin.model.AdminModel;

public class AdminDao {
	public int insert(AdminModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "insert into administrator(user_Administrator, password_Administrator) values(?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getUserAdmin());
		ps.setString(2, model.getPassAdmin());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int updatePass(AdminModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "update administrator set password_Administrator=? where user_Administrator=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getPassAdmin());
		ps.setString(2, model.getUserAdmin());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int delete(AdminModel model) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "delete from administrator where user_Administrator=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getUserAdmin());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public AdminModel selectByCode(AdminModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from administrator where user_Administrator = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, model.getUserAdmin());
        ResultSet rs = ps.executeQuery();
        AdminModel dbModel = null;
        while (rs.next()) {
            String userAdmin = rs.getString("user_Administrator");
            String passAdmin = rs.getString("password_Administrator");
            dbModel = new AdminModel();
            dbModel.setUserAdmin(userAdmin);
            dbModel.setPassAdmin(passAdmin);
        }
        rs.close();
        ps.close();
        conn.close();
        return dbModel;
	}
	public List<AdminModel> selectList(AdminModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from administrator where user_Administrator like ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%"+model.getUserAdmin()+"%");
        ResultSet rs = ps.executeQuery();
        List<AdminModel> list = new ArrayList<>();
        while (rs.next()) {
        	String userAdmin = rs.getString("user_Administrator");
        	AdminModel dbModel = new AdminModel();
        	dbModel.setUserAdmin(userAdmin);
            list.add(dbModel);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
	}
}
