package com.project.student.result.service;

import java.sql.SQLException;
import java.util.List;

import com.project.student.course.dao.CourseDao;
import com.project.student.course.model.CourseModel;
import com.project.student.result.dao.ResultDao;
import com.project.student.result.model.ResultModel;
import com.project.student.student.dao.StudentDao;
import com.project.student.student.model.StudentModel;

public class ResultService {
	private ResultDao dao = new ResultDao();
	private StudentDao stDao = new StudentDao();
	private CourseDao coDao = new CourseDao();
	public int insert(ResultModel model) throws ClassNotFoundException, SQLException {
		ResultModel countResult = dao.selectByCode(model);
		if(countResult==null) {
			String studentString = model.getStudentResult();
			String courseString = model.getCourseResult();
			StudentModel stModel = new StudentModel();
			stModel.setCodeStudent(studentString);
			CourseModel coModel = new CourseModel();
			coModel.setCodeCourse(courseString);
			StudentModel countStudent = stDao.selectByCode(stModel);
			CourseModel countCourse = coDao.selectByCode(coModel);
			if(countStudent==null)
				return 3;
			if(countCourse==null)
				return 4;
			if(model.getResultResult()=="")
				model.setResultResult("-1");
			double result = Double.valueOf(model.getResultResult());
			if(result < -1 || result > 100) {
				return 2;
			}else {
				return dao.insert(model);
			}
		}
		return 5;
	}
	public int delete(ResultModel model) throws ClassNotFoundException, SQLException {
		int re = dao.delete(model);
		return re;
	}
	public int update(ResultModel model) throws ClassNotFoundException, SQLException {
		double result = Double.valueOf(model.getResultResult());
		if(result<0 || result>100) {
			return 2;
		}else {
			return dao.update(model);
		}
	}
	public ResultModel selectByCode(ResultModel model) throws ClassNotFoundException, SQLException {
		ResultModel re = dao.selectByCode(model);
		return re;
	}
	public List<ResultModel> selectList(ResultModel model) throws ClassNotFoundException, SQLException {
		List<ResultModel> re = dao.selectList(model);
		return re;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	}
}
