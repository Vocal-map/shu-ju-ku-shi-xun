package com.project.student.student.servlet;

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

import com.project.student.student.model.StudentModel;
import com.project.student.student.service.StudentService;

@WebServlet("/student")
public class StudentServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentService service = new StudentService();
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
				List<StudentModel> list = selectList(req);
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
				// TODO: handle exception
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
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "sel":
			try {
				StudentModel dbModel = selectByCode(req);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", dbModel);
				JSONObject jsonObject = new JSONObject(map);
				String jsonString = jsonObject.toString();
				resp.getWriter().print(jsonString);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO: handle exception
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
	public List<StudentModel> selectList(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		StudentModel model = new StudentModel();
		String codeStudentString = req.getParameter("codeStudent");
		codeStudentString = codeStudentString == null ? "" : codeStudentString;
		model.setCodeStudent(codeStudentString);
		List<StudentModel> list = service.selectList(model);
		return list;
	}
	public int insert(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String codeStudentString = req.getParameter("codeStudent");
		String nameStudentString = req.getParameter("nameStudent");
		String sexStudentString = req.getParameter("sexStudent");
		String telStudentString = req.getParameter("telStudent");
		String academyStudentString = req.getParameter("academyStudent");
		String professionalStudentString = req.getParameter("professionalStudent");
		String gradeStudentString = req.getParameter("gradeStudent");
		String classStudentString = req.getParameter("classStudent");
		String dormitoryStudentString = req.getParameter("dormitoryStudent");
		StudentModel model = new StudentModel();
		model.setCodeStudent(codeStudentString);
		model.setNameStudent(nameStudentString);
		model.setSexStudent(sexStudentString);
		model.setTelStudent(telStudentString);
		model.setAcademyStudent(academyStudentString);
		model.setProfessionalStudent(professionalStudentString);
		model.setGradeStudent(gradeStudentString);
		model.setClassStudent(classStudentString);
		model.setDormitoryStudent(dormitoryStudentString);
		int count = service.insert(model);
		return count;
	}
	public int delete(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String codeStudentString = req.getParameter("codeStudent");
		StudentModel model = new StudentModel();
		model.setCodeStudent(codeStudentString);
		int count = service.delete(model);
		return count;
	}
	public StudentModel selectByCode(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String codeStudentString = req.getParameter("codeStudent");
		StudentModel model = new StudentModel();
		model.setCodeStudent(codeStudentString);
		StudentModel dbModel = service.selectByCode(model);
		return dbModel;
	}
	public int update(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String codeStudentString = req.getParameter("codeStudent");
		String nameStudentString = req.getParameter("nameStudent");
		String sexStudentString = req.getParameter("sexStudent");
		String telStudentString = req.getParameter("telStudent");
		String academyStudentString = req.getParameter("academyStudent");
		String professionalStudentString = req.getParameter("professionalStudent");
		String gradeStudentString = req.getParameter("gradeStudent");
		String classStudentString = req.getParameter("classStudent");
		String dormitoryStudentString = req.getParameter("dormitoryStudent");
		StudentModel model = new StudentModel();
		model.setCodeStudent(codeStudentString);
		model.setNameStudent(nameStudentString);
		model.setSexStudent(sexStudentString);
		model.setTelStudent(telStudentString);
		model.setAcademyStudent(academyStudentString);
		model.setProfessionalStudent(professionalStudentString);
		model.setGradeStudent(gradeStudentString);
		model.setClassStudent(classStudentString);
		model.setDormitoryStudent(dormitoryStudentString);
		int count = service.update(model);
		return count;
	}
}
