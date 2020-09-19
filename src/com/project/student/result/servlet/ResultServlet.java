package com.project.student.result.servlet;

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

import com.project.student.result.model.ResultModel;
import com.project.student.result.service.ResultService;


@WebServlet("/result")
public class ResultServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResultService service = new ResultService();
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
				List<ResultModel> list = selectList(req);
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
				ResultModel dbModel = selectByCode(req);
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
	public List<ResultModel> selectList(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		ResultModel model = new ResultModel();
		String studentResultString = req.getParameter("studentResult");
		String courseResultString = req.getParameter("courseResult");
		studentResultString = studentResultString == null ? "" : studentResultString;
		courseResultString = courseResultString == null ? "" : courseResultString;
		model.setStudentResult(studentResultString);
		model.setCourseResult(courseResultString);
		List<ResultModel> list = service.selectList(model);
		return list;
	}
	public int insert(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String studentResultString = req.getParameter("studentResult");
		String courseResultString = req.getParameter("courseResult");
		String resultResultString = req.getParameter("resultResult");
		ResultModel model = new ResultModel();
		model.setStudentResult(studentResultString);
		model.setCourseResult(courseResultString);
		model.setResultResult(resultResultString);
		int count = service.insert(model);
		return count;
	}
	public int delete(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String studentResultString = req.getParameter("studentResult");
		String courseResultString = req.getParameter("courseResult");
		ResultModel model = new ResultModel();
		model.setStudentResult(studentResultString);
		model.setCourseResult(courseResultString);
		int count = service.delete(model);
		return count;
	}
	public ResultModel selectByCode(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String studentResultString = req.getParameter("studentResult");
		String courseResultString = req.getParameter("courseResult");
		ResultModel model = new ResultModel();
		model.setStudentResult(studentResultString);
		model.setCourseResult(courseResultString);
		ResultModel dbModel = service.selectByCode(model);
		return dbModel;
	}
	public int update(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String studentResultString = req.getParameter("studentResult");
		String courseResultString = req.getParameter("courseResult");
		String resultResultString = req.getParameter("resultResult");
		ResultModel model = new ResultModel();
		model.setStudentResult(studentResultString);
		model.setCourseResult(courseResultString);
		model.setResultResult(resultResultString);
		int count = service.update(model);
		return count;
	}
}
