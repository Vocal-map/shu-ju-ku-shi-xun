package com.project.student.admin.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.project.student.admin.dao.AdminDao;
import com.project.student.admin.model.AdminModel;

import sun.misc.BASE64Encoder;

public class AdminService {
	private AdminDao dao = new AdminDao();
	
	public int insert(AdminModel model) throws ClassNotFoundException, SQLException {
		AdminModel aModel = new AdminModel();
		aModel.setUserAdmin("root");
		AdminModel dbModel = dao.selectByCode(aModel);
		if(dbModel.getPassAdmin().equals(encode(model.getRootPass()))) {
			return dao.insert(model);
		} else {
			return 2;
		}
	}
	
	public int delete(AdminModel model) throws ClassNotFoundException, SQLException {
		AdminModel dbModel = dao.selectByCode(model);
		String anObject = "root";
		if(dbModel.getUserAdmin().equals(anObject))
			return 2;
		int count = dao.delete(model);
		return count;
	}
	public AdminModel selectByCode(AdminModel model) throws ClassNotFoundException, SQLException {
		AdminModel dbModel = dao.selectByCode(model);
		return dbModel;
	}

	public int updatePass(AdminModel model) throws ClassNotFoundException, SQLException {
		return dao.updatePass(model);
	}
	public List<AdminModel> selectList(AdminModel model) throws ClassNotFoundException, SQLException {
		List<AdminModel> re = dao.selectList(model);
		return re;
	}
	public int login(AdminModel model) throws ClassNotFoundException, SQLException {
		AdminModel dbModel = dao.selectByCode(model);
		if(dbModel==null) {
			return 2;
		}
		String passString = model.getPassAdmin();
		passString = encode(passString);
		if(dbModel.getPassAdmin().equals(passString)) {
			return 1;
		} else {
			return 3;
		}
	}
	public String encode(String pass) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder b64 = new BASE64Encoder();
            return b64.encode(md5.digest(pass.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
