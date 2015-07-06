package com.Controller.UserController;

import java.util.Map;

import com.Controller.Controller;
import com.dao.Userdao.UserDaoImpl;

public class UserListController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		UserDaoImpl userdao = (UserDaoImpl)model.get("userdao");
		 
		model.put("users", userdao.getAllUsers());
		
		return "/USER/UserInfo.jsp";
	}
}
