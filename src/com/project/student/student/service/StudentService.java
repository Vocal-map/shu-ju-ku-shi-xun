package com.project.student.student.service;

import java.sql.SQLException;
import java.util.List;

import com.project.student.classes.dao.ClassesDao;
import com.project.student.classes.model.ClassesModel;
import com.project.student.dormitory.dao.DormitoryDao;
import com.project.student.dormitory.model.DormitoryModel;
import com.project.student.result.dao.ResultDao;
import com.project.student.result.model.ResultModel;
import com.project.student.student.dao.StudentDao;
import com.project.student.student.model.StudentModel;

public class StudentService {
	private StudentDao dao = new StudentDao();
	private ClassesDao classDao = new ClassesDao();
	private DormitoryDao dorDao = new DormitoryDao();
	private ResultDao reDao = new ResultDao();
	public int insert(StudentModel model) throws ClassNotFoundException, SQLException {
		StudentModel countStudent = dao.selectByCode(model);
		if(countStudent==null) {
			String classeString = model.getClassStudent();
			ClassesModel clModel = new ClassesModel();
			clModel.setCodeClass(classeString);
			ClassesModel countClasses = classDao.selectByCode(clModel);
			if(countClasses==null)
				return 3;
			String dormitoryString = model.getDormitoryStudent();
			DormitoryModel dorModel = new DormitoryModel();
			dorModel.setCodeDormitory(dormitoryString);
			DormitoryModel countDormitory = dorDao.selectByCode(dorModel);
			if(countDormitory==null)
				return 4;
			return dao.insert(model);
		}
		return 2;
	}
	public int delete(StudentModel model) throws ClassNotFoundException, SQLException {
		ResultModel reModel = new ResultModel();
		String codeString = model.getCodeStudent();
		reModel.setCourseResult(codeString);
		ResultModel countre = reDao.selectByCode(reModel);
		if(countre==null)
			return 2;
		int re = dao.delete(model);
		return re;
	}
	public int update(StudentModel model) throws ClassNotFoundException, SQLException {
		String classeString = model.getClassStudent();
		ClassesModel clModel = new ClassesModel();
		clModel.setCodeClass(classeString);
		ClassesModel countClasses = classDao.selectByCode(clModel);
		if(countClasses==null)
			return 2;
		String dormitoryString = model.getDormitoryStudent();
		DormitoryModel dorModel = new DormitoryModel();
		dorModel.setCodeDormitory(dormitoryString);
		DormitoryModel countDormitory = dorDao.selectByCode(dorModel);
		if(countDormitory==null)
			return 3;
		return dao.update(model);
	}
	public StudentModel selectByCode(StudentModel model) throws ClassNotFoundException, SQLException {
		StudentModel re = dao.selectByCode(model);
		return re;
	}
	public List<StudentModel> selectList(StudentModel model) throws ClassNotFoundException, SQLException {
		List<StudentModel> re = dao.selectList(model);
		return re;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	}
}
