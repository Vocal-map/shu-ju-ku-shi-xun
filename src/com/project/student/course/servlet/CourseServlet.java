package com.project.student.course.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

//import com.project.student.classes.model.ClassesModel;
//import com.project.student.classes.service.ClassesService;
import com.project.student.course.model.CourseModel;
import com.project.student.course.service.CourseService;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CourseService service = new CourseService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String actionString = req.getParameter("action");
		switch (actionString) {
		case "list":
			try {
				List<CourseModel> list = selectList(req);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("data", list);
				map.put("count", list.size());
				map.put("code", 0);
				JSONObject jsonObject = new JSONObject(map);
				String jsonString = jsonObject.toString();
				resp.getWriter().print(jsonString);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;

		case "add":
			try {
				int count = insert(req);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", count);
				JSONObject jsonObject = new JSONObject(map);
				String jsonString = jsonObject.toString();
				resp.getWriter().print(jsonString);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "del":
			try {
				int count = delete(req);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", count);
				JSONObject jsonObject = new JSONObject(map);
				String jsonString = jsonObject.toString();
				resp.getWriter().print(jsonString);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "sel":
			try {
				CourseModel dbModel = selectByCode(req);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", dbModel);
				JSONObject jsonObject = new JSONObject(map);
				String jsonString = jsonObject.toString();
				resp.getWriter().print(jsonString);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "upd":
			try {
				int count = update(req);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", count);
				JSONObject jsonObject = new JSONObject(map);
				String jsonString = jsonObject.toString();
				resp.getWriter().print(jsonString);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		}
		
	}
	public CourseModel selectByCode(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String codeCourseString = req.getParameter("codeCourse");
		CourseModel model = new CourseModel();
		model.setCodeCourse(codeCourseString);
		CourseModel dbModel = service.selectByCode(model);
		return dbModel;
	}
	public List<CourseModel> selectList(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		CourseModel model = new CourseModel();
		String nameCourseString = req.getParameter("nameCourse");
		nameCourseString = nameCourseString == null ? "" : nameCourseString;
		model.setNameCourse(nameCourseString);
		List<CourseModel> list = service.selectList(model);
		return list;
	}
	public int delete(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String codeCourseString = req.getParameter("codeCourse");
		CourseModel model = new CourseModel();
		model.setCodeCourse(codeCourseString);
		int count = service.delete(model);
		return count;
	}
	public int insert(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String codeCourseString = req.getParameter("codeCourse");
		String nameCourseString = req.getParameter("nameCourse");
		String creditCourseString = req.getParameter("creditCourse");
		String periodCourseString = req.getParameter("periodCourse");
		CourseModel model = new CourseModel();
		model.setCodeCourse(codeCourseString);
		model.setNameCourse(nameCourseString);
		model.setCreditCourse(creditCourseString);
		model.setPeriodCourse(periodCourseString);
		int count = service.insert(model);
		return count;
	}
	public int update(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String codeCourseString = req.getParameter("codeCourse");
		String nameCourseString = req.getParameter("nameCourse");
		String creditCourseString = req.getParameter("creditCourse");
		String periodCourseString = req.getParameter("periodCourse");
		CourseModel model = new CourseModel();
		model.setCodeCourse(codeCourseString);
		model.setNameCourse(nameCourseString);
		model.setCreditCourse(creditCourseString);
		model.setPeriodCourse(periodCourseString);
		int count = service.update(model);
		return count;
	}
}
