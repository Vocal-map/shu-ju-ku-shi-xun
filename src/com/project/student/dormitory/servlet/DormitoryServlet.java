package com.project.student.dormitory.servlet;

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

import com.project.student.dormitory.model.DormitoryModel;
import com.project.student.dormitory.service.DormitoryService;

@WebServlet("/dormitory")
public class DormitoryServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DormitoryService service = new DormitoryService();
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
				List<DormitoryModel> list = selectList(req);
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
				DormitoryModel dbModel = selectByCode(req);
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
	public DormitoryModel selectByCode(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String codeDormitoryString = req.getParameter("codeDormitory");
		DormitoryModel model = new DormitoryModel();
		model.setCodeDormitory(codeDormitoryString);
		DormitoryModel dbModel = service.selectByCode(model);
		return dbModel;
	}
	public List<DormitoryModel> selectList(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		DormitoryModel model = new DormitoryModel();
		String codeDormitoryString = req.getParameter("codeDormitory");
		codeDormitoryString = codeDormitoryString == null ? "" : codeDormitoryString;
		model.setCodeDormitory(codeDormitoryString);
		List<DormitoryModel> list = service.selectList(model);
		return list;
	}
	public int delete(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String codeDormitoryString = req.getParameter("codeDormitory");
		DormitoryModel model = new DormitoryModel();
		model.setCodeDormitory(codeDormitoryString);
		int count = service.delete(model);
		return count;
	}
	public int insert(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String codeDormitorysString = req.getParameter("codeDormitory");
		String capacityDormitoryString = req.getParameter("capacityDormitory");
		String priceDormitoryString = req.getParameter("priceDormitory");
		DormitoryModel model = new DormitoryModel();
		model.setCodeDormitory(codeDormitorysString);
		model.setCapacityDormitory(capacityDormitoryString);
		model.setPriceDormitory(priceDormitoryString);
		int count = service.insert(model);
		return count;
	}
	public int update(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String codeDormitorysString = req.getParameter("codeDormitory");
		String capacityDormitoryString = req.getParameter("capacityDormitory");
		String priceDormitoryString = req.getParameter("priceDormitory");
		DormitoryModel model = new DormitoryModel();
		model.setCodeDormitory(codeDormitorysString);
		model.setCapacityDormitory(capacityDormitoryString);
		model.setPriceDormitory(priceDormitoryString);
		int count = service.update(model);
		return count;
	}
}
