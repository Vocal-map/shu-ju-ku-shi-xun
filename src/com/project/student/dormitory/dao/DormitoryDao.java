package com.project.student.dormitory.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.student.dormitory.model.DormitoryModel;

public class DormitoryDao {
	public int insert(DormitoryModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "insert into dormitory(code_dormitory, capacity_dormitory, price_dormitory) values(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getCodeDormitory());
		ps.setString(2, model.getCapacityDormitory());
		ps.setString(3, model.getPriceDormitory());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int delete(DormitoryModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "delete from dormitory where code_dormitory = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getCodeDormitory());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public int update(DormitoryModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "update dormitory set capacity_dormitory=?, price_dormitory=?  where code_dormitory = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, model.getCapacityDormitory());
		ps.setString(2, model.getPriceDormitory());
		ps.setString(3, model.getCodeDormitory());
		int count = ps.executeUpdate(); // sql影响的条数
		ps.close();
		conn.close();
		return count;
	}
	public DormitoryModel selectByCode(DormitoryModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from dormitory where code_dormitory=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, model.getCodeDormitory());
        ResultSet rs = ps.executeQuery();
        DormitoryModel doModel = null;
        while(rs.next()) {
        	String codeDormitory = rs.getString("code_dormitory");
        	String capacityDormitory = rs.getString("capacity_dormitory");
        	String priceDormitory = rs.getString("price_dormitory");
        	doModel = new DormitoryModel();
        	doModel.setCodeDormitory(codeDormitory);
        	doModel.setCapacityDormitory(capacityDormitory);
        	doModel.setPriceDormitory(priceDormitory);
        }
        rs.close();
        ps.close();
        conn.close();
        return doModel;
	}
	public List<DormitoryModel> selectList(DormitoryModel model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/student_system?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=Hongkong";
		String user = "root";
		String pass = "findfish";
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select * from dormitory where code_dormitory like ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%"+model.getCodeDormitory()+"%");
        ResultSet rs = ps.executeQuery();
        List<DormitoryModel> list = new ArrayList<>();
        while (rs.next()) {
        	String codeDormitory = rs.getString("code_dormitory");
        	String capacityDormitory = rs.getString("capacity_dormitory");
        	String priceDormitory = rs.getString("price_dormitory");
        	DormitoryModel doModel = new DormitoryModel();
        	doModel.setCodeDormitory(codeDormitory);
        	doModel.setCapacityDormitory(capacityDormitory);
        	doModel.setPriceDormitory(priceDormitory);
            list.add(doModel);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}
}
