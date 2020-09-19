package com.project.student.count.servlet;

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

import com.project.student.count.model.CountModel;
import com.project.student.count.service.CountService;

@WebServlet("/count")
public class CountServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CountService service = new CountService();
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
				List<CountModel> list = selectList(req);
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
		case "listDor":
			try {
				List<CountModel> list = selectListDor(req);
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
		case "listRe":
			try {
				List<CountModel> list = selectListRe(req);
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
		}
	}
	public List<CountModel> selectList(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		//CountModel model = new CountModel();
		List<CountModel> list = service.selectList();
		return list;
	}
	public List<CountModel> selectListDor(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		//CountModel model = new CountModel();
		List<CountModel> list = service.selectListDor();
		return list;
	}
	public List<CountModel> selectListRe(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		//CountModel model = new CountModel();
		List<CountModel> list = service.selectListRe();
		return list;
	}
}
