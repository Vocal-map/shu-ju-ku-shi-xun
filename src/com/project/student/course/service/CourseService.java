package com.project.student.course.service;

import java.sql.SQLException;
import java.util.List;


import com.project.student.course.dao.CourseDao;
import com.project.student.course.model.CourseModel;
import com.project.student.result.dao.ResultDao;
import com.project.student.result.model.ResultModel;

public class CourseService {
	private CourseDao dao = new CourseDao();
	private ResultDao reDao = new ResultDao();
	
	public int insert(CourseModel model) throws ClassNotFoundException, SQLException {
		CourseModel countModel = dao.selectByCode(model);
		if(countModel==null)
			return dao.insert(model);
		 return 2;
	}
	public int delete(CourseModel model) throws ClassNotFoundException, SQLException {
		ResultModel reModel = new ResultModel();
		String codeString = model.getCodeCourse();
		reModel.setCourseResult(codeString);
		ResultModel countre = reDao.selectByCode(reModel);
		if(countre==null)
			return 2;
		int re = dao.delete(model);
		return re;
	}
	public int update(CourseModel model) throws ClassNotFoundException, SQLException {
		int re = dao.update(model);
		return re;
	}
	public CourseModel selectByCode(CourseModel model) throws ClassNotFoundException, SQLException {
		CourseModel re = dao.selectByCode(model);
		return re;
	}
	public List<CourseModel> selectList(CourseModel model) throws ClassNotFoundException, SQLException {
		List<CourseModel> re = dao.selectList(model);
		return re;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	}
}
