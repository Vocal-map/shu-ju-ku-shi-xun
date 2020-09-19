package com.project.student.admin.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import com.project.student.admin.model.AdminModel;
import com.project.student.admin.service.AdminService;

import sun.misc.BASE64Encoder;

@WebServlet("/administrator") 
public class AdminServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService service = new AdminService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String actionString = req.getParameter("action");
		System.out.println(actionString);
		switch (actionString) {
		case "list":
			try {
				List<AdminModel> list = selectList(req);
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
				e.printStackTrace();
			}
			break;
		case "login":
			try {
				int count = login(req);
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
		case "login2":
			try {
				int count = login2(req);
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
				AdminModel dbModel = selectByCode(req);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", dbModel);
				JSONObject jsonObject = new JSONObject(map);
				String jsonString = jsonObject.toString();
				resp.getWriter().print(jsonString);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "pass":
			try {
				int count = pass(req);
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
	private int login2(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String passAdmin = req.getParameter("passAdmin");
		AdminModel model = new AdminModel();
		model.setUserAdmin("root");
		model.setPassAdmin(passAdmin);
		return service.login(model);
	}

	public int pass(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String userAdmin = req.getParameter("userAdmin");
		String passOldString = req.getParameter("passOld");
		passOldString = encode(passOldString);
		String passNewString = req.getParameter("passNew");
		String passNew2String = req.getParameter("passNew2");
		if(!passNew2String.equals(passNewString))
			return 3;
		passNewString = encode(passNewString);
		AdminModel model = new AdminModel();
		String user2Admin = "root";
		model.setUserAdmin(user2Admin);
		AdminModel dbModel = service.selectByCode(model);
		if(dbModel.getPassAdmin().equals(passOldString)) {	
			AdminModel modelN = new AdminModel();
			modelN.setUserAdmin(userAdmin);
			modelN.setPassAdmin(passNewString);
			return service.updatePass(modelN);
		}else {
			return 2;
		}	
	}
	public AdminModel selectByCode(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String userAdmin = req.getParameter("userAdmin");
		AdminModel model = new AdminModel();
		model.setUserAdmin(userAdmin);
		AdminModel dbModel = service.selectByCode(model);
		return dbModel;
	}
	public List<AdminModel> selectList(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		AdminModel model = new AdminModel();
		String userAdmin = req.getParameter("userAdmin");
		userAdmin = userAdmin == null ? "" : userAdmin;
		model.setUserAdmin(userAdmin);
		List<AdminModel> list = service.selectList(model);
		return list;
	}
	private int login(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String userAdmin = req.getParameter("userAdmin");
		String passAdmin = req.getParameter("passAdmin");
		AdminModel model = new AdminModel();
		model.setUserAdmin(userAdmin);
		model.setPassAdmin(passAdmin);
		return service.login(model);
	}
	 

	public int delete(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		String userAdmin = req.getParameter("userAdmin");
		AdminModel model = new AdminModel();
		model.setUserAdmin(userAdmin);
		int count = service.delete(model);
		return count;
	}
	
	public int insert(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		String userAdmin = req.getParameter("userAdmin");
		String passAdmin = req.getParameter("passAdmin");
		String passAdmin2 = req.getParameter("passAdmin2");
		if(!passAdmin.equals(passAdmin2))
			return 3;
		String rootPass  = req.getParameter("rootPass");
		passAdmin= passAdmin==null?"123456":passAdmin;
		passAdmin = encode(passAdmin);
		AdminModel model = new AdminModel();
		model.setUserAdmin(userAdmin);
		model.setPassAdmin(passAdmin);
		model.setRootPass(rootPass);
		int count = service.insert(model);
		return count;
	}

	public String encode(String pass) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder b64 = new BASE64Encoder();
            return b64.encode(md5.digest(pass.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
