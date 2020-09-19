package com.project.student.dormitory.service;

import java.sql.SQLException;
import java.util.List;

import com.project.student.dormitory.dao.DormitoryDao;
import com.project.student.dormitory.model.DormitoryModel;

public class DormitoryService {
	private DormitoryDao dao = new DormitoryDao();
	
	public int insert(DormitoryModel model) throws ClassNotFoundException, SQLException {
		DormitoryModel countModel = dao.selectByCode(model);
		if(countModel==null)
			return dao.insert(model);
		return 2;
	}
	public int delete(DormitoryModel model) throws ClassNotFoundException, SQLException {
		int re = dao.delete(model);
		return re;
	}
	public int update(DormitoryModel model) throws ClassNotFoundException, SQLException {
		int re = dao.update(model);
		return re;
	}
	public DormitoryModel selectByCode(DormitoryModel model) throws ClassNotFoundException, SQLException {
		DormitoryModel re = dao.selectByCode(model);
		return re;
	}
	public List<DormitoryModel> selectList(DormitoryModel model) throws ClassNotFoundException, SQLException {
		List<DormitoryModel> re = dao.selectList(model);
		return re;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	}
}
