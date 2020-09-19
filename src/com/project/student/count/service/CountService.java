package com.project.student.count.service;

import java.sql.SQLException;
import java.util.List;

import com.project.student.count.dao.CountDao;
import com.project.student.count.model.CountModel;

public class CountService {
	private CountDao dao = new CountDao();
	public List<CountModel> selectList() throws ClassNotFoundException, SQLException {
		return dao.selectList();
	}
	
	public List<CountModel> selectListDor() throws ClassNotFoundException, SQLException {
		return dao.selectListDor();
	}
	public List<CountModel> selectListRe() throws ClassNotFoundException, SQLException {
		return dao.selectListRe();
	}
}
