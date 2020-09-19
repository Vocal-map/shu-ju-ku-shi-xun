package com.project.student.classes.servlet;

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

import com.project.student.classes.service.ClassesService;
import com.project.student.classes.model.ClassesModel;

@WebServlet("/classes")
public class ClassesServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClassesService service = new ClassesService();
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
				List<ClassesModel> list = selectList(req);
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
				ClassesModel dbModel = selectByCode(req);
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
	public ClassesModel selectByCode(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String codeClassString = req.getParameter("codeClass");
		System.out.println(codeClassString);
		ClassesModel model = new ClassesModel();
		model.setCodeClass(codeClassString);
		ClassesModel dbModel = service.selectByCode(model);
		System.out.println(dbModel.getCodeClass());
		return dbModel;
	}
	public List<ClassesModel> selectList(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		ClassesModel model = new ClassesModel();
		String codeClassString = req.getParameter("codeClass");
		String departmentClassString = req.getParameter("departmentClass");
		String instructorClassString = req.getParameter("instructorClass");
		codeClassString = codeClassString == null ? "" : codeClassString;
		departmentClassString = departmentClassString == null ? "" : departmentClassString;
		instructorClassString = instructorClassString == null ? "" : instructorClassString;
		model.setCodeClass(codeClassString);
		model.setDepartmentClass(departmentClassString);
		model.setInstructorClass(instructorClassString);
		List<ClassesModel> list = service.selectList(model);
		return list;
	}
	public int delete(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String codeClassString = req.getParameter("codeClass");
		ClassesModel model = new ClassesModel();
		model.setCodeClass(codeClassString);
		int count = service.delete(model);
		return count;
	}
	public int insert(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String codeClassString = req.getParameter("codeClass");
		String departmentClassString = req.getParameter("departmentClass");
		String instructorClassString = req.getParameter("instructorClass");
		ClassesModel model = new ClassesModel();
		model.setCodeClass(codeClassString);
		model.setDepartmentClass(departmentClassString);
		model.setInstructorClass(instructorClassString);
		int count = service.insert(model);
		return count;
	}
	public int update(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String codeClassString = req.getParameter("codeClass");
		String departmentClassString = req.getParameter("departmentClass");
		String instructorClassString = req.getParameter("instructorClass");
		ClassesModel model = new ClassesModel();
		model.setCodeClass(codeClassString);
		model.setDepartmentClass(departmentClassString);
		model.setInstructorClass(instructorClassString);
		int count = service.update(model);
		return count;
	}
}
