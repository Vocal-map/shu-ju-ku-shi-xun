package com.project.student.classes.service;

import java.sql.SQLException;
import java.util.List;

import com.project.student.classes.dao.ClassesDao;
import com.project.student.classes.model.ClassesModel;

public class ClassesService {
	ClassesDao dao = new ClassesDao();
	
	public int insert(ClassesModel model) throws ClassNotFoundException, SQLException {
		ClassesModel countModel = dao.selectByCode(model);
		if(countModel==null)
			return dao.insert(model);
		return 2;
	}
	public int delete(ClassesModel model) throws ClassNotFoundException, SQLException {
		int re = dao.delete(model);
		return re;
	}
	public int update(ClassesModel model) throws ClassNotFoundException, SQLException {
		int re = dao.update(model);
		return re;
	}
	public ClassesModel selectByCode(ClassesModel model) throws ClassNotFoundException, SQLException {
		ClassesModel re = dao.selectByCode(model);
		return re;
	}
	public ClassesModel selectByInstructor(ClassesModel model) throws ClassNotFoundException, SQLException {
		ClassesModel re = dao.selectByInstructor(model);
		return re;
	}
	public List<ClassesModel> selectList(ClassesModel model) throws ClassNotFoundException, SQLException {
		List<ClassesModel> re = dao.selectList(model);
		return re;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ClassesDao cDao = new ClassesDao();
		ClassesModel cModel = new ClassesModel();
		cModel.setCodeClass("¼Æ¿Æ1803");
		ClassesModel re = cDao.selectByCode(cModel);
		System.out.printf("%s,%s,%s", re.getCodeClass(),re.getDepartmentClass(),re.getInstructorClass());
	}
}
